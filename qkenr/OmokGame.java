import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import javax.swing.*;

public class OmokGame extends JPanel {

    final int SIZE = 19;
    final int CELL = 30;
    final int OFFSET = 15;

    int[][] board = new int[SIZE][SIZE];

    int playerStone;
    int aiStone;
    int turn = 1;

    int hoverX=-1,hoverY=-1;

    Stack<Point> history=new Stack<>();

    Image boardImg;
    Image blackStone;
    Image whiteStone;

    Random rand=new Random();

    public OmokGame(){

        setPreferredSize(new Dimension(SIZE*CELL,SIZE*CELL));

        boardImg=new ImageIcon("board.png").getImage();
        blackStone=new ImageIcon("black.jpg").getImage();
        whiteStone=new ImageIcon("white.jpg").getImage();

        if(rand.nextBoolean()){
            playerStone=1;
            aiStone=2;
            JOptionPane.showMessageDialog(this,"플레이어: 검은돌");
        }else{
            playerStone=2;
            aiStone=1;
            JOptionPane.showMessageDialog(this,"플레이어: 흰돌");
        }

        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){

                if(turn!=playerStone) return;

                int x=Math.round((e.getX()-OFFSET)/(float)CELL);
                int y=Math.round((e.getY()-OFFSET)/(float)CELL);

                if(!inBoard(x,y)) return;
                if(board[y][x]!=0) return;

                if(playerStone==1 && isForbidden(x,y)){
                    JOptionPane.showMessageDialog(null,"금수 자리");
                    return;
                }

                placeStone(x,y,playerStone);

                if(checkWin(x,y,playerStone)){
                    JOptionPane.showMessageDialog(null,"플레이어 승리");
                    reset();
                    return;
                }

                turn=aiStone;
                aiMove();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseMoved(MouseEvent e){

                hoverX=Math.round((e.getX()-OFFSET)/(float)CELL);
                hoverY=Math.round((e.getY()-OFFSET)/(float)CELL);

                repaint();
            }
        });

        setFocusable(true);

        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){

                if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Z)
                    undo();
            }
        });

        if(playerStone==2){
            aiMove();
            turn=playerStone;
        }
    }

    void placeStone(int x,int y,int stone){
        board[y][x]=stone;
        history.push(new Point(x,y));
        repaint();
    }

    void undo(){

        if(history.size()<2) return;

        for(int i=0;i<2;i++){
            Point p=history.pop();
            board[p.y][p.x]=0;
        }

        turn=playerStone;
        repaint();
    }

    void aiMove(){

        Point best=findBestMove();

        placeStone(best.x,best.y,aiStone);

        if(checkWin(best.x,best.y,aiStone)){
            JOptionPane.showMessageDialog(null,"AI 승리");
            reset();
        }

        turn=playerStone;
    }

    Point findBestMove(){

        int bestScore=Integer.MIN_VALUE;
        Point best=new Point(SIZE/2,SIZE/2);

        for(Point p:getCandidates()){

            board[p.y][p.x]=aiStone;

            int score=minimax(2,false,Integer.MIN_VALUE,Integer.MAX_VALUE);

            board[p.y][p.x]=0;

            if(score>bestScore){
                bestScore=score;
                best=p;
            }
        }

        return best;
    }

    int minimax(int depth,boolean maximizing,int alpha,int beta){

        if(depth==0) return evaluateBoard();

        if(maximizing){

            int maxEval=Integer.MIN_VALUE;

            for(Point p:getCandidates()){

                board[p.y][p.x]=aiStone;

                int eval=minimax(depth-1,false,alpha,beta);

                board[p.y][p.x]=0;

                maxEval=Math.max(maxEval,eval);
                alpha=Math.max(alpha,eval);

                if(beta<=alpha) break;
            }

            return maxEval;
        }
        else{

            int minEval=Integer.MAX_VALUE;

            for(Point p:getCandidates()){

                board[p.y][p.x]=playerStone;

                int eval=minimax(depth-1,true,alpha,beta);

                board[p.y][p.x]=0;

                minEval=Math.min(minEval,eval);
                beta=Math.min(beta,eval);

                if(beta<=alpha) break;
            }

            return minEval;
        }
    }

    int evaluateBoard(){

        int score=0;

        score+=evaluatePlayer(aiStone);
        score-=evaluatePlayer(playerStone);

        return score;
    }

    int evaluatePlayer(int stone){

        int score=0;

        int[][] dir={{1,0},{0,1},{1,1},{1,-1}};

        for(int y=0;y<SIZE;y++){
            for(int x=0;x<SIZE;x++){

                if(board[y][x]!=stone) continue;

                for(int[] d:dir){

                    int count=1;

                    for(int i=1;i<5;i++){

                        int nx=x+d[0]*i;
                        int ny=y+d[1]*i;

                        if(!inBoard(nx,ny)) break;

                        if(board[ny][nx]==stone)
                            count++;
                        else break;
                    }

                    if(count==5) score+=100000;
                    if(count==4) score+=10000;
                    if(count==3) score+=1000;
                    if(count==2) score+=100;
                }
            }
        }

        return score;
    }

    List<Point> getCandidates(){

        List<Point> list=new ArrayList<>();

        for(int y=0;y<SIZE;y++){
            for(int x=0;x<SIZE;x++){

                if(board[y][x]!=0) continue;

                if(hasNeighbor(x,y))
                    list.add(new Point(x,y));
            }
        }

        if(list.isEmpty())
            list.add(new Point(SIZE/2,SIZE/2));

        return list;
    }

    boolean hasNeighbor(int x,int y){

        for(int dy=-2;dy<=2;dy++)
            for(int dx=-2;dx<=2;dx++){

                int nx=x+dx;
                int ny=y+dy;

                if(inBoard(nx,ny)&&board[ny][nx]!=0)
                    return true;
            }

        return false;
    }

    boolean isForbidden(int x,int y){

        board[y][x]=1;

        int open3=countOpen(x,y,3);
        int open4=countOpen(x,y,4);

        board[y][x]=0;

        return open3>=2 || open4>=2;
    }

    int countOpen(int x,int y,int len){

        int count=0;

        int[][] dir={{1,0},{0,1},{1,1},{1,-1}};

        for(int[] d:dir){

            int c=1;

            for(int i=1;i<5;i++){

                int nx=x+d[0]*i;
                int ny=y+d[1]*i;

                if(!inBoard(nx,ny)) break;

                if(board[ny][nx]==1) c++;
                else break;
            }

            for(int i=1;i<5;i++){

                int nx=x-d[0]*i;
                int ny=y-d[1]*i;

                if(!inBoard(nx,ny)) break;

                if(board[ny][nx]==1) c++;
                else break;
            }

            if(c==len) count++;
        }

        return count;
    }

    boolean checkWin(int x,int y,int player){

        int[][] dir={{1,0},{0,1},{1,1},{1,-1}};

        for(int[] d:dir){

            int count=1;

            for(int i=1;i<5;i++){

                int nx=x+d[0]*i;
                int ny=y+d[1]*i;

                if(!inBoard(nx,ny)) break;

                if(board[ny][nx]==player)
                    count++;
                else break;
            }

            for(int i=1;i<5;i++){

                int nx=x-d[0]*i;
                int ny=y-d[1]*i;

                if(!inBoard(nx,ny)) break;

                if(board[ny][nx]==player)
                    count++;
                else break;
            }

            if(count>=5) return true;
        }

        return false;
    }

    boolean inBoard(int x,int y){
        return x>=0 && y>=0 && x<SIZE && y<SIZE;
    }

    void reset(){

        for(int y=0;y<SIZE;y++)
            for(int x=0;x<SIZE;x++)
                board[y][x]=0;

        history.clear();

        repaint();

        if(playerStone==2){
            aiMove();
            turn=playerStone;
        }else{
            turn=playerStone;
        }
    }

    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        g.drawImage(boardImg,0,0,SIZE*CELL,SIZE*CELL,null);

        for(int y=0;y<SIZE;y++)
            for(int x=0;x<SIZE;x++){

                int px=OFFSET+x*CELL-CELL/2;
                int py=OFFSET+y*CELL-CELL/2;

                if(board[y][x]==1)
                    g.drawImage(blackStone,px,py,CELL,CELL,null);

                if(board[y][x]==2)
                    g.drawImage(whiteStone,px,py,CELL,CELL,null);
            }

        if(turn==playerStone && inBoard(hoverX,hoverY) && board[hoverY][hoverX]==0){

            Graphics2D g2=(Graphics2D)g;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));

            int px=OFFSET+hoverX*CELL-CELL/2;
            int py=OFFSET+hoverY*CELL-CELL/2;

            if(playerStone==1)
                g2.drawImage(blackStone,px,py,CELL,CELL,null);
            else
                g2.drawImage(whiteStone,px,py,CELL,CELL,null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        }
    }

    public static void main(String[] args){

        JFrame frame=new JFrame("Omok AI");

        OmokGame game=new OmokGame();

        frame.add(game);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}