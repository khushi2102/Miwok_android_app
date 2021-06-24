package com.example.miwok;

public class word {
   private String miwok,english;
private int res_id= noImage;
private static final int noImage=-1;
private int music_id;

    word(String M, String E,int res, int music)
    {
        res_id=res;
        miwok=M;
        english=E;
        music_id=music;
    }



    word(String M, String E)
    {

        miwok=M;
        english=E;
    }

    public String getMiwok()
    {
        return miwok;
    }

    public String getEnglish()
    {
        return english;
    }

    public int getRes_id()
    {
        return res_id;
    }

    public boolean hasImage(){
        if(res_id!=noImage)
        return true;
        else return false;
    }
    public int getMusic_id()
    {
        return music_id;
    }

}
