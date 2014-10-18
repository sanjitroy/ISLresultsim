
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sroy13 
 */
public class Solution {
    static String team_names[]={
        "Atletico de Kolkata",
        "Mumbai FC          ",
        "North East United  ",
        "Kerala FC          ",
        "Delhi Dynamos      ",
        "FC Pune            ",
        "FC Goa             ",
        "Chennaiyin FC      "
    };
    //matches played, won,drew,lost
    static int table[][]={
        {2,2,0,0},
        {1,0,0,1},
        {2,1,0,1},
        {1,0,0,1},
        {1,0,1,0},
        {1,0,1,0},
        {1,0,0,1},
        {1,1,0,0}
    };
    static double ratings[]={
        8,
        8.2,
        7.3,
        8,
        7.9,
        7.5,
        7.2,
        8.5};
    static double goals[] = {
        2.5,
        0,
        0.5,
        0,
        0,
        0,
        1,
        2};
    
    static double goalsdiff[] = {5,-3,-1,-1,0,0,-1,1};
    static double form[] ={
        1.6183,
        -0.5061,
        0.6228,
        -0.5228,
        0.5370,
        0.5629,
        -0.6244,
        0.5595};
    static int matches[] = new int[8];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        FileInputStream input = new FileInputStream("D:\\Documents\\Files\\codes\\simulator\\Solution\\src\\ISLF.txt");
        int c,xc=0,ht=0;
        System.out.println("Fixture List");
        while ((c = input.read()) != -1) {
            if(Character.getNumericValue(c)!=-1){
                xc++;
                if(xc%2==0){
                    calculate(ht,Character.getNumericValue(c));
                }
                else{
                    ht= Character.getNumericValue(c);
                }
                //System.out.println(Character.getNumericValue(c));
            }
            //System.out.println(Character.getNumericValue(c));
        }
        /**
        for(int i=0;i<20;i++){
          System.out.println("Home team ... ");
          int ht = Integer.parseInt(br.readLine());
          System.out.println("Away team ... ");
          int at = Integer.parseInt(br.readLine());
          calculate(ht,at);
        }**/
        //String word = br.readLine();
        /**
        
        for(int i=0;i<n;i++){
            int number = Integer.parseInt(br.readLine());
            //String word = br.readLine();
            //arr[i]= calculate(word);
            //answers[i]=calculate(number);
            answers[i] = calculate(number);
        }
        
        for(int i=0;i<n;i++){
            System.out.println(answers[i]);
        }
        **/
        //calculate(word);
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
     
    
    public static void calculate(int h,int a){
        int h_matches = matches[h];
        int a_matches = matches[a];
        matches[a]++;
        matches[h]++;
        double h_goals = goals[h];
        double a_goals = goals[a];
        double h_rating = ratings[h];
        double a_rating = ratings[a];
        double h_form = form[h];
        double a_form = form[a];
        double h_power = (h_rating/(h_rating+a_rating));
        double a_power = (a_rating/(h_rating+a_rating));
        double havg_goals = 1;
        double aavg_goals = 1 ;
        /**
        if(h_goals==0){
            goals[h]= h_power;
            h_goals = h_power;
        }
        if(a_goals==0){
            goals[a]= a_power;
            a_goals = a_power ;
        }
        **/
        havg_goals = (h_power *h_goals)+h_form ;
        aavg_goals = (a_power *a_goals)+a_form ;
        int h_goals_rd = (int)Math.round(havg_goals);
        int a_goals_rd = (int)Math.round(aavg_goals);
        
        //System.out.println(team_names[a]+" : "+ a_goals_rd );
        //System.out.println(team_names[h]+" : "+ h_goals_rd );
        
        if(havg_goals>0){
        goals[h]=(goals[h]+havg_goals)/2;
        
        }
        else{
            goals[h]=goals[h]/2;
        }
        if(aavg_goals>0){
        goals[a]=(goals[a]+aavg_goals)/2;
        }
        else{
            goals[a]=goals[a]/2;
        }
        form[h] = ((havg_goals*h_power)-(aavg_goals*a_power)/2);
        form[a] = ((aavg_goals*a_power)-(havg_goals*h_power)/2);
        if(h_goals_rd>a_goals_rd){
            form[h]=form[h]+0.1 ;
            table[h][0]=table[h][0]+1;
            table[h][1]=table[h][1]+1;
            table[a][0]=table[a][0]+1;
            table[a][3]=table[a][3]+1;
        }
        else if(h_goals_rd<a_goals_rd){
            form[a]=form[a]+0.1 ;
            table[a][0]=table[a][0]+1;
            table[a][1]=table[a][1]+1;
            table[h][0]=table[h][0]+1;
            table[h][3]=table[h][3]+1;
        }
        else{
            form[h]=form[h]+0.05;
            form[a]=form[a]+0.05;
            table[h][0]=table[h][0]+1;
            table[h][2]=table[h][1]+1;
            table[a][0]=table[a][0]+1;
            table[a][2]=table[a][3]+1;
        }
        //System.out.println("Home form : "+form[h]);
        //System.out.println("Away form : "+form[a]);
        System.out.println("Team name         \t \t P \t W \t D \t L \t PTS \t Goals(avg)");
        for(int i=0;i<8;i++){
            System.out.println(team_names[i]+"\t \t"+table[i][0]+"\t "+table[i][1]+" \t"+table[i][2]+" \t"+table[i][3]+" \t"+ (table[i][1]*3+table[i][2])+ " \t" +Math.round(goals[i]*table[i][0])+" \t");
        }
        /**
        if(goals[h]!=0)
        goals[h]=(goals[h]+havg_goals)/2;
        else
           goals[h]=goals[h]+havg_goals;
        if(goals[a]!=0)
        goals[a]=(goals[a]+aavg_goals)/2;
        else
           goals[a]=goals[a]+aavg_goals;
        //System.out.println(h_matches);
        **/
    }    
}
