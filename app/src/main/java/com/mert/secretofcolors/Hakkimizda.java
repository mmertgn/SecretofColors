package com.mert.secretofcolors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class Hakkimizda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element versionElement = new Element();
        versionElement.setTitle("Sürüm 0.1");

        Element adsElement = new Element();
        adsElement.setTitle("Kübra Özdemir - Mustafa Mert Gün");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.ic_launcher)
                .setDescription("Hedef doğrultusunda projemiz çocukların resim ve müziğe olan bilgilerini, becerilerini ve yeteneklerini ortaya çıkaracaktır. Kendi resimlerini yaptıkları gibi müziklerini de yaptıklarını gösterecektir.\n")
                .addItem(versionElement)
                .addItem(adsElement)
                .addGroup("Bize Ulaşın")
                .addEmail("secretofcolors@gmail.com")
                .addWebsite("http://secretofcolors.github.io/")
                .addFacebook("secretofcolors")
                .addTwitter("secretofcolors")
                .addYoutube("secretofcolors")
                .addPlayStore("com.mert.secretofcolors")
                .addInstagram("secretofcolors")
                .addGitHub("secretofcolors")
                .create();
        setContentView(aboutPage);
    }
}
