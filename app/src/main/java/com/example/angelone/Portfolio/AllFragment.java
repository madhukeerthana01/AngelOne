package com.example.angelone.Portfolio;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.angelone.R;

public class AllFragment extends Fragment {
    Button equitybtn,sgbbtn,mutualfundsbtn;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        equitybtn = view.findViewById(R.id.equitybtn);
        sgbbtn = view.findViewById(R.id.sgbbtn);
        mutualfundsbtn = view.findViewById(R.id.mutualfundsbtn);
        equitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PortfolioFragment portfolioFragment = (PortfolioFragment) getParentFragment();
                if (portfolioFragment != null) {
                    portfolioFragment.getViewPager().setCurrentItem(1);
                }
            }
        });


        sgbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PortfolioFragment portfolioFragment = (PortfolioFragment) getParentFragment();
                if (portfolioFragment != null) {
                    portfolioFragment.getViewPager().setCurrentItem(3);
                }
            }
        });

        mutualfundsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PortfolioFragment portfolioFragment = (PortfolioFragment) getParentFragment();
                if (portfolioFragment != null) {
                    portfolioFragment.getViewPager().setCurrentItem(2);
                }
            }
        });
        return view;
    }
}