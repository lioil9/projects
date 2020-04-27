class GuessGame{
    static String[] ROOT_MENU = {"请选择难度等级\n","1. 0～9\n","2. 0~99\n","3. 0~999\n","0. 退出\n"};
    static int ROOT_MENU_LEN = ROOT_MENU.length;

    enum FlowStatus{
        ROOt, QUIT
    }
    
    static void displayRootMenu(){
        for(int i=0; i<ROOT_MENU_LEN; i++){
            System.out.print(ROOT_MENU[i]);
        }
    }


    public static void main(String[] args) {

        displayRootMenu();
        
    }
}