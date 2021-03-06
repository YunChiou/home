package com.example.asus.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mPage == 1) {
            View view = inflater.inflate(R.layout.fragment_page, container, false);
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvTitle.setText("Fragment #" + mPage);

            view.setOnClickListener(tabClickListener);
            return view;
        }
        else if (mPage == 2) {
            View view = inflater.inflate(R.layout.table_fragment, container, false);
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvTitle.setText("Fragment #" + mPage);
            return view;
        }
        return null;
    }

    private View.OnClickListener tabClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getVisibility() == View.VISIBLE) {
                v.setVisibility( View.INVISIBLE );
            } else {
                v.setVisibility( View.VISIBLE );
            }
        }
    };

}