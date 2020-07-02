package com.tech_reeturaj.navigationdrawer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tech_reeturaj.navigationdrawer.Module;
import com.tech_reeturaj.navigationdrawer.ModuleAdapter;
import com.tech_reeturaj.navigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle(R.string.menu_home);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        dummyModule();
        //recyclerView.setAdapter(new ModuleAdapter(modules));

        ModuleAdapter moduleAdapter = new ModuleAdapter(modules);

        moduleAdapter.setOnDeepClickListner(new ModuleAdapter.Deeplistner() {
            @Override
            public void deepClick(View view, int position) {
                Module module = modules.get(position);
                if (module.getName().equalsIgnoreCase("gallery"))
                {
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_nav_home_to_nav_gallery);
                }
                else if (module.getName().equalsIgnoreCase("slideshow"))
                {
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_nav_home_to_nav_slideshow);
                }
                else if (module.getName().equalsIgnoreCase("favorites"))
                {
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_nav_home_to_nav_favorite);
                }
                else if (module.getName().equalsIgnoreCase("logout"))
                {
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_nav_home_to_nav_logout);
                }
            }
        });

        recyclerView.setAdapter(moduleAdapter);

    }

    List<Module> modules = new ArrayList<>();

    private void dummyModule()
    {
        modules = new ArrayList<>();
        Module m1 = new Module();
        m1.setName("Gallery");
        m1.setImage(R.drawable.gallery);

        Module m2 = new Module();
        m2.setName("Slideshow");
        m2.setImage(R.drawable.slideshow);

        Module m3 = new Module();
        m3.setName("Favorites");
        m3.setImage(R.drawable.favorite);

        Module m4 = new Module();
        m4.setName("Logout");
        m4.setImage(R.drawable.logout);

        modules.add(m1);
        modules.add(m2);
        modules.add(m3);
        modules.add(m4);
    }
}