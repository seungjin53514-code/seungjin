package sj;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GameGUI extends JFrame {

    Player player = new Player("나");
    Player enemy = new Player("적");

    Deck deck = new Deck();

    JPanel cardPanel = new JPanel(new GridLayout(1,7));
    JTextArea log = new JTextArea();

    JLabel turnLabel = new JLabel("",SwingConstants.CENTER);

    JProgressBar playerHP = new JProgressBar(0,100);
    JProgressBar enemyHP = new JProgressBar(0,100);

    ImageIcon swordIcon = new ImageIcon("sj/sword.png");
    ImageIcon shieldIcon = new ImageIcon("sj/shield.png");

    Card selectedAttack;
    List<Card> selectedDefense = new ArrayList<>();

    Card enemyAttack;

    enum TurnState{ATTACK,DEFENSE}
    TurnState state;

    public GameGUI(){

        setTitle("Card Battle");
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        turnLabel.setFont(new Font("Arial",Font.BOLD,30));

        add(turnLabel,BorderLayout.NORTH);
        add(cardPanel,BorderLayout.CENTER);

        JPanel hpPanel = new JPanel(new GridLayout(2,1));

        playerHP.setStringPainted(true);
        enemyHP.setStringPainted(true);

        hpPanel.add(enemyHP);
        hpPanel.add(playerHP);

        add(hpPanel,BorderLayout.WEST);

        log.setEditable(false);

        add(new JScrollPane(log),BorderLayout.EAST);

        JButton confirm = new JButton("확인");

        confirm.addActionListener(e->{

            if(state==TurnState.ATTACK)
                finishAttack();

            if(state==TurnState.DEFENSE)
                finishDefense();
        });

        add(confirm,BorderLayout.SOUTH);

        startGame();

        setVisible(true);
    }

    void startGame(){

        for(int i=0;i<7;i++)
            player.addCard(deck.draw());

        updateHP();

        playerTurn();
    }

    void updateHP(){

        playerHP.setValue(player.hp);
        enemyHP.setValue(enemy.hp);

        playerHP.setString("내 HP "+player.hp);
        enemyHP.setString("적 HP "+enemy.hp);
    }

    void playerTurn(){

        state = TurnState.ATTACK;

        turnLabel.setText("내 공격 턴");

        refreshCards();
    }

    void enemyTurn(){

        int atk = 5 + new Random().nextInt(15);

        enemyAttack = new Card("적 공격","attack",atk,0);

        log.append("적 공격 : "+atk+"\n");

        playerDefense();
    }

    void playerDefense(){

        state = TurnState.DEFENSE;

        turnLabel.setText("방어 카드 선택");

        refreshCards();
    }

    void finishAttack(){

        if(selectedAttack==null){

            log.append("공격 카드 선택\n");
            return;
        }

        int damage = selectedAttack.attack;

        enemy.hp -= damage;

        log.append("공격 "+damage+"\n");

        player.removeCard(selectedAttack);

        selectedAttack = null;

        updateHP();

        checkGame();

        enemyTurn();
    }

    void finishDefense(){

        if(selectedDefense.size()==0){

            log.append("방어 카드 선택\n");
            return;
        }

        int totalDefense = 0;

        for(Card c:selectedDefense)
            totalDefense += c.defense;

        int damage = enemyAttack.attack - totalDefense;

        if(damage<0) damage=0;

        player.hp -= damage;

        log.append("방어 "+totalDefense+"\n");
        log.append("데미지 "+damage+"\n");

        for(Card c:selectedDefense)
            player.removeCard(c);

        selectedDefense.clear();

        updateHP();

        checkGame();

        refillCards();

        playerTurn();
    }

    void refreshCards(){

        cardPanel.removeAll();

        for(Card c:player.hand){

            JButton btn = new JButton();

            if(c.type.equals("attack")){

                btn.setIcon(swordIcon);
                btn.setText("공격 "+c.attack);
            }

            if(c.type.equals("defense")){

                btn.setIcon(shieldIcon);
                btn.setText("방어 "+c.defense);
            }

            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);

            btn.addActionListener(e->{

                if(state==TurnState.ATTACK && c.type.equals("attack")){

                    if(selectedAttack==c){

                        selectedAttack=null;
                        btn.setBorder(null);

                    }else{

                        selectedAttack=c;
                        btn.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
                    }
                }

                if(state==TurnState.DEFENSE && c.type.equals("defense")){

                    if(selectedDefense.contains(c)){

                        selectedDefense.remove(c);
                        btn.setBorder(null);

                    }else{

                        selectedDefense.add(c);
                        btn.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
                    }
                }

            });

            cardPanel.add(btn);
        }

        cardPanel.revalidate();
        cardPanel.repaint();
    }

    void refillCards(){

        int attack=0;
        int defense=0;

        for(Card c:player.hand){

            if(c.type.equals("attack")) attack++;
            if(c.type.equals("defense")) defense++;
        }

        while(attack<3){

            player.addCard(deck.draw());
            attack++;
        }

        while(defense<3){

            player.addCard(deck.draw());
            defense++;
        }

        while(player.hand.size()<7)
            player.addCard(deck.draw());
    }

    void checkGame(){

        if(player.hp<=0){

            JOptionPane.showMessageDialog(this,"패배");
            System.exit(0);
        }

        if(enemy.hp<=0){

            JOptionPane.showMessageDialog(this,"승리");
            System.exit(0);
        }
    }

    public static void main(String[] args){

        new GameGUI();
    }
}