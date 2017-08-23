package com.demo.neilhu.dialect;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by NeilHu on 2016/11/8.
 */
public class CategoryAdapter extends FragmentPagerAdapter{
    private Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return  mContext.getString(R.string.category_numbers);
        }
        else if (position == 1) {
            return mContext.getString(R.string.category_family);
        } else if (position == 2) {
            return mContext.getString(R.string.category_colors);
        } else if (position == 3) {
            return mContext.getString(R.string.category_phrases);
        } else {
            return "账户";
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new Numbers_frament();
        }
        else if (position == 1) {
            return new Family_fragment();
        } else if (position == 2) {
            return new Colors_fragment();
        } else if (position == 3) {
            return new Phrases_fragment();
        } else {
            return new HomeFragment();
        }
    }
}
