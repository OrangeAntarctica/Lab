package 软件工程;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lab2_{
    public static void main(String args[]) throws IOException {
        String code_line = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the path of the file:");
        code_line = sc.next();
        String reader;
        FileInputStream inputStream = new FileInputStream(code_line);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String keywords[]=  {"auto","double","int","struct","break","else","long","switch",
                "case","enum","register","typedef","char","extern","return","union",
                "const","float","short","unsigned","continue","for","signed","void",
                "default","goto","sizeof","volatile","do","if","while","static","elseif"};
        StringBuilder ss = new StringBuilder();
        while ((reader = bufferedReader.readLine())!=null){
            reader = reader.replace(':', ' ');
            if(reader.matches("(.*)//(.*)")){
                String b[] = reader.split("//");
                ss.append(b[0]+" ");
            }else{
                ss.append(reader+" ");
            }
        }
        inputStream.close();
        bufferedReader.close();

        String s = ss.toString();
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(s);
        while(m.find()){
            s=s.replace(m.group()," ");
            p=Pattern.compile("\"(.*?)\"");
            m=p.matcher(s);
        }
        p=Pattern.compile("/\\**(.*?)/");
        m=p.matcher(s);
        while(m.find()){
            s=s.replace(m.group()," ");
            m=p.matcher(s);
        }
        if(s.isEmpty())
        {System.out.println("Wrong Format");
            System.exit(0);
        }
        s=s.replace("["," ");s=s.replace("]"," ");s=s.replace("-"," ");s=s.replace("*"," ");s=s.replace("/"," ");s=s.replace("+"," ");s=s.replace(">"," ");s=s.replace("="," ");s=s.replace("!"," ");s=s.replace(":"," ");s=s.replace("\\"," ");s= s.replaceAll("[^a-zA-Z]", " ");
        String []code_arr=s.split("[  ' ']");
        int mode_choose = 0;
        System.out.println("Please enter the level from 1-4:");
        mode_choose = sc.nextInt();
        int mode[] = {1,2,3,4};
        if(mode_choose == mode[0]) {
        	First(code_arr,keywords);
        }
        else if(mode_choose == mode[1]) {
        	First(code_arr,keywords);
        	Second(code_arr,keywords);
        }
        else if(mode_choose == mode[2]) {
        	First(code_arr,keywords);
        	Second(code_arr,keywords);
        	Third(code_arr,keywords);
        }
        else if(mode_choose == mode[3]) {
        	First(code_arr,keywords);
        	Second(code_arr,keywords);
        	Third(code_arr,keywords);
        	Fourth(code_arr,keywords);
        }
    }
    
    public static int First(String arr[],String key[]) {
    	int num_keyWords = 0;
            for(int i=0;i<arr.length;i++) {
                for(int j=0;j<key.length;j++){
                    if(arr[i].equals(key[j]))
                    {
                        num_keyWords ++;
                    }
                }
            }
            System.out.println("total num: "+num_keyWords);
            return num_keyWords;
    }
    
    public static int Second(String arr[],String key[]) {
            int num_switch = 0;
            for(int i=0;i<arr.length;i++) {
                if(arr[i].equals("switch")) {
                    num_switch ++ ;
                }
            }
            System.out.println("switch num:  "+num_switch);
            Vector vec_case = new Vector(4);
            int num_case = 0;
            int index = -1;
            for(int i=0;i<arr.length;i++) {
                if(arr[i].equals("switch")){
                    index++;
                    num_case=0;
                }
                if(arr[i].equals("case")){
                    num_case++;
                    vec_case.add(index,num_case);
                }
            }
            System.out.print("case num:  ");
            if(num_switch == 0) {
                System.out.println(0);
            }
            else {
                for(int t=0;t<=index;t++){
                    System.out.print(vec_case.get(t)+" ");
                }
                System.out.println();
            }
            return num_switch;
        }
    
    public static int Third(String arr[],String key[]) {
        	int num_if_else = 0;
            for(int i=0;i<arr.length;i++)
            {
                if(i!=0) {
                    if(arr[i].equals("if")&&!arr[i-1].equals("else")) {
                        int init = i + 1;
                        if(i<arr.length-3) {
                            while((!(arr[i+1].equals("else")&&!arr[i+2].equals("if")))&&i<arr.length-3) {
                                i++;
                            }
                        }
                        else {
                            break;
                        }
                        int tmp = i;
                        boolean flag = false;
                        boolean flag2 = true;
                        for(int t = init;t<tmp;t++) {
                            if(arr[t].equals("if")&&!arr[t-1].equals("else")) {
                                for(int k = t;k<tmp;k++) {
                                    if(arr[k].equals("else")) {
                                        flag2 = false;
                                        break;
                                    }
                                }
                                if(flag2) num_if_else ++ ;
                                flag = true;
                                break;
                            }
                        }
                        if(flag) {
                            if(i<arr.length - 3) {
                                while(!(arr[i+1].equals("else")&&!arr[i+2].equals("if"))) {
                                    i++;
                                }
                            }
                            else {
                                break;
                            }
                            num_if_else ++;
                        }
                    }
                }
            }
            System.out.println("if-else num: "+num_if_else);
            return num_if_else;
        }
    
    public static int Fourth(String arr[],String key[]) {
    	 
        	int num_else = 0;
        	int num_if_else_if_else = 0;
        	int num_if_else = 0;
            for(int i=0;i<arr.length;i++)
            {
                if(i!=0) {
                    if(arr[i].equals("if")&&!arr[i-1].equals("else")) {
                        int init = i + 1;
                        if(i<arr.length-3) {
                            while((!(arr[i+1].equals("else")&&!arr[i+2].equals("if")))&&i<arr.length-3) {
                                i++;
                            }
                        }
                        else {
                            break;
                        }
                        int tmp = i;
                        boolean flag = false;
                        boolean flag2 = true;
                        for(int t = init;t<tmp;t++) {
                            if(arr[t].equals("if")&&!arr[t-1].equals("else")) {
                                for(int k = t;k<tmp;k++) {
                                    if(arr[k].equals("else")) {
                                        flag2 = false;
                                        break;
                                    }
                                }
                                if(flag2) num_if_else ++ ;
                                flag = true;
                                break;
                            }
                        }
                        if(flag) {
                            if(i<arr.length - 3) {
                                while(!(arr[i+1].equals("else")&&!arr[i+2].equals("if"))) {
                                    i++;
                                }
                            }
                            else {
                                break;
                            }
                            num_if_else ++;
                        }
                    }
                }
            }

            for(int i=0;i<arr.length;i++) {
                if(i < arr.length - 1) {
                    if(arr[i].equals("else")&&!arr[i+1].equals("if")){
                        num_else ++;
                    }
                }
                if(i == arr.length-1) {
                    if(arr[i].equals("else")) {
                        num_else ++;
                    }
                }
            }
            num_if_else_if_else = num_else - num_if_else;
            System.out.print("if -else if- else num: "+num_if_else_if_else);
            return num_if_else_if_else;
        }
    }