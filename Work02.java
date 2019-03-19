package Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Work02 {
    
    static int Num;
    static MyPoint []mp;
    static MyOrder []mo;
    static double lowCost;
    
    public static void main(String[] args) {
        for(int i = 0; i < 7; i++) {
            long start_time = System.currentTimeMillis();
            read("input" + i + ".txt");
            lowCost = 200;
            tsp(1);
            long end_time = System.currentTimeMillis();
            Print(lowCost);
            System.out.println("걸린 시간: " + (end_time - start_time)/1000.0);
            
        }
        
    }
    
    public static void read(String str) {
        try {
            Scanner    inFile = new Scanner(new File(str));
            Num = inFile.nextInt();
            mp = new MyPoint[Num]; // 모든 점들의 배열
            mo = new MyOrder[Num+1]; // 순서들을 모은 배열
            for(int i = 0; i < Num; i++) {
                mp[i] = new MyPoint();
                mp[i].x = inFile.nextInt();
                mp[i].y = inFile.nextInt();
                mo[i] = new MyOrder();
                mo[i].num = i;
            }
            mo[Num] = new MyOrder();
            
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file");
            System.exit(9);
        }
    }
    
    public static void tsp(int node){
        
        if(node == Num - 1) {
            double sum = 0;
            for(int i = 0; i < Num; i++) {
                sum += dist(mo[i].num, mo[i+1].num);
                if(lowCost <= sum)
                    return;
                if(i == Num-1)
                    lowCost = min(lowCost,sum);
            }
            return;
        }
        
        for(int i = node; i < Num; i++) {
            swap(mo,node,i);
            tsp(node+1);
            swap(mo,node,i);
        }
        
    }
    
    public static void swap(MyOrder []mo, int num1, int num2) {
        
        int tmp = mo[num1].num;
        mo[num1].num = mo[num2].num;
        mo[num2].num = tmp;
        
    }
    
    public static double min(double m1, double m2) {
        
        if(m1 <= m2)
            return m1;
        for(int i = 0; i < Num; i++)
            mo[i].r_num = mo[i].num;
        return m2;
        
    }
    
    public static void Print(double x) {
        System.out.println("answer:");
        System.out.println(x);
        System.out.print("[");
        
        for(int i = 0; i < Num - 1; i++)
            System.out.print(mo[i].r_num + ", ");
        
        System.out.println(mo[Num-1].r_num + "]");
    }
    
    public static double dist(int n1,int n2) {
        
        int Diff_x = mp[n1].x - mp[n2].x;
        int Diff_y = mp[n1].y - mp[n2].y;
        double result = Math.sqrt((Diff_x*Diff_x)+(Diff_y*Diff_y));
        return result;
        
    }
}
