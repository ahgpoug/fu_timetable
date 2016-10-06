package com.ahgpoug.fu_timetable;

import android.Manifest;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ProgressDialog loadingDialog;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Serialization.Deserialize();

        setupLoadingDialog();
        initializeComponents();
        setEvents();
        pager.setCurrentItem(getNowWeek());
    }

    private void setupLoadingDialog(){
        loadingDialog = new ProgressDialog(MainActivity.this);
        loadingDialog.setMessage("Загрузка...");
        loadingDialog.setCancelable(false);
        loadingDialog.setInverseBackgroundForced(false);
    }
    private void initializeComponents(){
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();

        Dexter.initialize(MainActivity.this);
    }

    private void setEvents(){
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        /*Dexter.checkPermissions(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);*/
    }

    private int getNowWeek(){
        if (GlobalVariables.mainList.size() > 0){
            SimpleDateFormat fmtIn = new SimpleDateFormat("dd/MM/yyyy");

            for (int i = 0; i < GlobalVariables.mainList.size(); i++){
                try {
                    Date weekDate = fmtIn.parse(GlobalVariables.mainList.get(i).getWeekName().substring(0, 9));

                    Calendar cal = Calendar.getInstance();
                    int weekNow = cal.get(Calendar.WEEK_OF_YEAR);

                    cal.setTime(weekDate);
                    int weekNum = cal.get(Calendar.WEEK_OF_YEAR);

                    if (weekNow == weekNum)
                        return i;
                } catch (ParseException e){
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter  {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(MainActivity.this, position);
        }

        @Override
        public int getCount() {
            return GlobalVariables.mainList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (GlobalVariables.mainList.size() > 0)
                return GlobalVariables.mainList.get(position).getWeekName();
            else
                return "";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sync) {
            ReadPDF readPdf = new ReadPDF();
            readPdf.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    class ReadPDF extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            PDFReader.getData(getApplicationInfo().dataDir);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pagerAdapter.notifyDataSetChanged();
            loadingDialog.dismiss();
        }
    }
}
