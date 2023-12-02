package pe.usat.moviles.rapidisimoapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pe.usat.moviles.rapidisimoapp.R;
import pe.usat.moviles.rapidisimoapp.adapter.SolicitudAdapter;
import pe.usat.moviles.rapidisimoapp.databinding.FragmentOrderBinding;
import pe.usat.moviles.rapidisimoapp.model.Solicitud;
import pe.usat.moviles.rapidisimoapp.response.SolicitudListadoActivasResponse;
import pe.usat.moviles.rapidisimoapp.retrofit.ApiService;
import pe.usat.moviles.rapidisimoapp.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;

    RecyclerView recyclerViewSolicitudes;
    SolicitudAdapter solicitudAdapter;
    List<Solicitud> solicitudesLista = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayoutSolicitudes;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //binding = FragmentOrderBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        //Configurar el recyclerview
        recyclerViewSolicitudes = view.findViewById(R.id.recyclerViewSolicitudes);
        recyclerViewSolicitudes.setLayoutManager(new LinearLayoutManager(getContext()));
        solicitudAdapter = new SolicitudAdapter(solicitudesLista, true);
        recyclerViewSolicitudes.setAdapter(solicitudAdapter);

        //Configurar el swipeRefreshLayoutClientes
        swipeRefreshLayoutSolicitudes = view.findViewById(R.id.swipeRefreshLayoutSolicitudes);

        //Reconozca el evento onRefresh
        swipeRefreshLayoutSolicitudes.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listarSolicitudes(); //Refrescar el contenido de la lista
                swipeRefreshLayoutSolicitudes.setRefreshing(false); //Desactivar la animación
            }
        });
        swipeRefreshLayoutSolicitudes.setColorSchemeResources(R.color.primaryColor, R.color.primaryColorDark, R.color.colorAccent);



        listarSolicitudes();

        return view;
    }

    private void listarSolicitudes(){
        int conductorId = 1;

        ApiService apiService = RetrofitClient.createService();
        Call<SolicitudListadoActivasResponse> call = apiService.listadoSolicitudesAtendidas(conductorId);
        call.enqueue(new Callback<SolicitudListadoActivasResponse>() {
            @Override
            public void onResponse(Call<SolicitudListadoActivasResponse> call, Response<SolicitudListadoActivasResponse> response) {
                if (response.code() == 200) {
                    boolean status = response.body().isStatus();
                    if (status) {
                        solicitudesLista.clear();
                        solicitudesLista.addAll(Arrays.asList(response.body().getData()));

                        solicitudAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.e("Error al acceder al servicio web", response.message());
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SolicitudListadoActivasResponse> call, Throwable t) {
                Log.e("Falla al conectarse al servicio web", t.getMessage());
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}