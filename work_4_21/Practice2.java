import java.util.Arrays;

public class Practice2 {
    /**
     * 根据传入的参数来将数组升序或降序排列
     * @param arr
     * @param isAsc
     */
    static void sort(int arr[], boolean isAsc) {
        //用冒泡排序，当isAsc为true时进行升序的冒泡排序
        if(isAsc){
            for(int i=0; i<arr.length-1; i++){
                for(int j=0; j<arr.length-i-1; j++){
                    if(arr[j] > arr[j+1]){
                        int temp = arr[j+1];
                        arr[j+1] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        //用冒泡排序，当isAsc为false时进行降序的冒泡排序
        }else{
            for(int i=0; i<arr.length-1; i++){
                for(int j=0; j<arr.length-i-1; j++){
                    if(arr[j] < arr[j+1]){
                        int temp = arr[j+1];
                        arr[j+1] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }
    /**
     * 重载上述的sortArr方法，用来实现数组升序排列
     * 用冒泡排序，当用此方法仅输入数组时会默认进行升序排序
     * @param arr
     */
    static void sort(int arr[]) {
        for(int i=0; i<arr.length-1; i++){
            for(int j=0; j<arr.length-i-1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    /**
     * 将数组中的元素按照每五个换行，并且以字符串拼接的方式打印
     * @param arr
     */
    public static void print(int arr[]){
        for(int i=0; i<arr.length; i++){
            System.out.print("["+i+"]="+arr[i]+"  ");
            //当输出第五个元素后进行换行
            if((i+1)%5 == 0)
                System.out.println();
        }
    }
    public static void main(String[] args){
        int[] arr = {13,26,-3,4,54,26,37,18,69,-10};
        System.out.println("未排序的数组为：");
        print(arr);
        sort(arr, false);
        System.out.println("降序排列后数组为：");
        print(arr);
        sort(arr);
        System.out.println("升序排列后数组为：");
        print(arr);
    }

}