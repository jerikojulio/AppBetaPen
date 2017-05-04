package com.example.lenovo.appbeta.Controllers;

/**
 * Created by LENOVO on 11/20/2016.
 */
public class TableListSemua {

    public int calculateList(Integer listLength, String[] articleIndex, Integer position) {
        //filter population start
        int a = 0;
        for (int i = 0; i < listLength; i++){
            a=a+1;
        }

        a=a+1;
        Integer[]realList=new Integer[a];


        Integer temp = 0;
        for (int i=0 ; i<listLength;i++){
            int articleIndexInt = Integer.parseInt(articleIndex[i]);
            articleIndexInt = articleIndexInt-1;

            for (int b=0 ; b<listLength;b++){
                if (articleIndexInt==b)
                {
                    temp=temp+1;
                    realList[temp]=b;
                }
            }
        }

        //filter population end
        return realList[position];
    }

    public int calculateListWithFilter(Integer listLength, String[] kategoriList, Integer position,String kategori) {

        switch (kategori) {
            case "SD":
                break;
            case "SMP":
                break;
            case "SMA":
                break;
            case "Mahasiswa":
                break;
            case "Umum":
                break;

        }

        //filter population start
        int a = 0;
        for (int i = 0; i < listLength; i++){
            if (kategoriList[i].equals(kategori))
            {
                a=a+1;
            }
        }

        a=a+1;
        Integer[]realList=new Integer[a];

        Integer temp = 0;
        for (int i=0 ; i<listLength;i++){
            if (kategoriList[i].equals(kategori))
            {
                temp=temp+1;
                realList[temp]=i;
            }
        }

        //filter population end
        return realList[position];
    }

}
