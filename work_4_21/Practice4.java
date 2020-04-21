import java.util.Arrays;

public class Practice4 {
    /**
     * 定义一个方法，将int型数组中重复的数只保留一个
     * @param arr
     * @return
     */
    public static int[] filtArray(int[] arr){
        //先将原数组的长度进行保存
        int count =  arr.length;
        //从数组下标0开始遍历，定义j为其下个元素，查找数组中的重复元素
        for(int i=0; i<count; i++){
            for(int j=i+1; j<count; j++){
                /**
                 * 当找到重复元素后，将当前重复元素与数组最后一个元素进行交换，
                 * 并且将保存的数组元素的长度-1；
                 * 并且要和刚刚交换后的元素比较是否重复
                 */
                if(arr[i] == arr[j]){
                    arr[j] = arr[count-1];
                    count--;
                    j--;
                }
            }
        }
        //定义一个新的数组，长度为刚才保存删除后的长度，然后将前半段不重复的元素赋值给新数组
        int[] newarr = new int[count];
        for(int i=0; i<newarr.length; i++){
            newarr[i] = arr[i];
        }
        return newarr;
    }
    /**
     * 有1.5元钱兑换1分，2分，5分硬币100枚，每种面值至少一个，请输出所有的兑换方案，并统计方案的总数
     */
    public static void coinChange(){
        int count = 0;
        //设定1分硬币为i枚，则最多不超过98枚，从1分只有1枚开始遍历
        for(int i=1; i<=98; i++){
            //设定2分硬币为j枚，最多也不能超过98枚，则可以得到剩下的5分硬币为100-i-j枚
            for(int j=1; j<=98; j++){
                //设定当前i和j以及剩下的硬币的总金额为a
                int a = i+2*j+(100-i-j)*5;
                //当总金额为150分，并且5分硬币大于0时将方案打印输出，并统计当前方案数
                if(a == 150 && (100-i-j)>0){
                    count++;
                    System.out.printf("第%2d种方案：", count);
                    System.out.println("1分"+i+"枚，2分"+j+"枚，5分"+(100-i-j)+"枚");
                }
            }
        }
        System.out.println("一共有"+count+"种兑换方案");
    }
    public static void main(String[] args){
        coinChange();

        int[] arr = {1,3,6,3,7,9,1,2,2};
        System.out.println("原数组为："+Arrays.toString(arr));
        int[] newarr = filtArray(arr); 
        System.out.println("删除重复元素后："+Arrays.toString(newarr));
    }
}