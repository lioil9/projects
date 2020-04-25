class StringBuffer1{
    private char[] str;     //定义字符数组
    private int capacity = 10;   //定义初识容量
    private int len = 0;    //定义输入字符的长度
    
    /**
     * 构造初始容量字符数组
     */
    public StringBuffer1(){
        this.str = new char[capacity];
    }

    /**
     * 接收字符串，如果超出数组容量，进行扩容
     */
    void append(String str){
        while((len+str.length()) > capacity){
            capacity += len+str.length();
            char[] newStr = new char[capacity];
            System.arraycopy(this.str, 0, newStr, 0, len);
            this.str = newStr; 
        }
        char[] temp = str.toCharArray();
        System.arraycopy(temp, 0, this.str, len, temp.length);
        len += temp.length;
    }

    /**
     * 接收字符，超出容量扩容
     */
    void append(char str){
        if(len == capacity){
            char[] newStr = new char[++capacity];
            System.arraycopy(this.str, 0, newStr, 0, len);
            this.str = newStr; 
        }
        this.str[len] = str;
        len++;
    }

    /**
     * 将字符数组返回为一个字符串
     */
    public String toString() {
        return new String(str);
    }

    /**
     * 将字符数组清空并将长度指向为0
     */
    void clear(){
        this.str = new char[capacity];
        len = 0;
    }

    /**
     * 输入内容反转，并返回字符串
     */
    String reverse(){
        for(int i=0; i<len/2; i++){
            char temp = str[i];
            str[i] = str[len-1-i];
            str[len-1-i] = temp;
        }
        return new String(str);
    }

    /**
     * 指定范围将字符串进行反转
     */
    String reverse(int from, int to){
        for(int i=from; i<(to+from)/2; i++){
            char temp = str[i];
            str[i] = str[to-1];
            str[to-1] = temp;
        }
        return new String(str);
    }

    public static void main(String[] args) {
        StringBuffer1 sb = new StringBuffer1();
        sb.append('s');
        sb.append("dgrwcbh");
        System.out.println(sb.toString());
        sb.reverse(2,4);
        System.out.println(sb.toString());
        sb.reverse();
        System.out.println(sb.toString());
        sb.reverse(2,4);
        System.out.println(sb.toString());
        sb.clear();
        sb.append('s');
        System.out.println(sb.toString());
        
    }
}