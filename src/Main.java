public class Main {
    public static void main(String[] args) {

        String sValue = "";
        char cValue;

        for (int i = 1; i < 1000; i++) {
            String sBaksu = "";
            int nCount = 0;

            sValue = i + "";

            for (int j = 0; j < sValue.length(); j++) {
                cValue = sValue.charAt(j);

                if (cValue == '3' || cValue == '6' || cValue == '9') {
                    nCount = nCount + 1;
                }
            }

            if (nCount > 0) {
                for (int k = 1; k <= nCount; k++) {
                    sBaksu = sBaksu + "짝";
                }
                System.out.println(i + sBaksu);
            } else {
                System.out.println(i);
            }
        }
    }
}