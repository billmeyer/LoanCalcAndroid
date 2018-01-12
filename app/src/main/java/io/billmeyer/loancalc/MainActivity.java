package io.billmeyer.loancalc;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import io.billmeyer.loancalc.fragments.AmortizationFragment;
import io.billmeyer.loancalc.fragments.LoanFragment;
import io.billmeyer.loancalc.fragments.NoDataFragment;

public class MainActivity extends AppCompatActivity
{
    LoanFragment loanFragment;
    AmortizationFragment amortizationFragment;
    NoDataFragment noDataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loanFragment = new LoanFragment();
        openFragment(loanFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle presses on the action bar items
        switch (item.getItemId())
        {
            case R.id.amortization:
                if (loanFragment.validate())
                {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mLoan", loanFragment.getLoan());

                    amortizationFragment = new AmortizationFragment();
                    amortizationFragment.setArguments(bundle);
                    openFragment(amortizationFragment);
                }
                else
                {
                    noDataFragment = new NoDataFragment();
                    openFragment(noDataFragment);
                }
                return true;

            case R.id.home:
                openFragment(loanFragment);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openFragment(final Fragment fragment)
    {
        FragmentManager mgr = getFragmentManager();

        FragmentTransaction xact = mgr.beginTransaction();
        xact.replace(R.id.fragment_container, fragment);
        xact.addToBackStack(null);
        xact.commit();
    }
}
