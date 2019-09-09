package com;

/**
 * User:wangmeng
 * Date:2019/8/15
 * Time:16:50
 * Version:2.x
 * Description:TODO
 **/
public class Run {
    private final static int [] temp = {1,2,5,8,6,2,11,15,2,6,2,11,15,5,1,8,6};
    public static void main(String[] args) {
        Run r = new Run();
        r.locateAllAndPrint();
    }
    private void locateAllAndPrint () {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            int h = temp[i];
            boolean repeated = false;
            for (int j = 0; j < i; j++) {
                if (temp[j] == h) {
                    repeated = true;
                    break;
                }
            }
            if (!repeated) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < temp.length; j++) {
                    if (h == temp[j]) {
                        sb.append(",").append(j);
                    }
                }
                sb.insert(0, h).insert(0, "[").append(']');
                System.out.println(sb.toString());
                stringBuilder.append(sb.toString()+":");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
