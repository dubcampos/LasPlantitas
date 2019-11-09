package com.codigo.recplants.Abaptadores;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codigo.recplants.Fragmentos.PrevencionFragment;
import com.codigo.recplants.Fragmentos.ProcedimientoFragment;
import com.codigo.recplants.Fragmentos.RespuestaFragment;

public class RespuestaTabAdapter extends FragmentPagerAdapter {
    int cantidad_tab;

    public RespuestaTabAdapter(FragmentManager fm, int cantidad_tab) {
        super(fm);
        this.cantidad_tab = cantidad_tab;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new RespuestaFragment();
        }else if(i == 1) {
            return new ProcedimientoFragment();
        }else  if (i == 2) {
            return new PrevencionFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Pagina " + position;

    }
}
