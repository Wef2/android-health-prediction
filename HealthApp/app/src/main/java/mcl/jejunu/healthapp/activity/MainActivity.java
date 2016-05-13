package mcl.jejunu.healthapp.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.fragment.CurrentFragment;
import mcl.jejunu.healthapp.fragment.GoalFragment;
import mcl.jejunu.healthapp.fragment.InformationFragment;
import mcl.jejunu.healthapp.fragment.PastFragment;
import mcl.jejunu.healthapp.fragment.PredictionFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle("목표");
        getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new GoalFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_goal) {
            toolbar.setTitle("목표");
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new GoalFragment()).commit();
        } else if (id == R.id.nav_past) {
            toolbar.setTitle("과거");
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new PastFragment()).commit();
        } else if (id == R.id.nav_current) {
            toolbar.setTitle("현재");
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new CurrentFragment()).commit();
        } else if (id == R.id.nav_prediction) {
            toolbar.setTitle("미래");
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new PredictionFragment()).commit();
        } else if (id == R.id.nav_information) {
            toolbar.setTitle("나의 정보");
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new InformationFragment()).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
