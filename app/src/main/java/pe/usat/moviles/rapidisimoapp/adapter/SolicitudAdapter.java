package pe.usat.moviles.rapidisimoapp.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.usat.moviles.rapidisimoapp.R;
import pe.usat.moviles.rapidisimoapp.model.Solicitud;

public class SolicitudAdapter extends RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder>{

    private List<Solicitud> solicitudLista;
    private boolean isEditable;
    public int posicionItemSeleccionado;

    public SolicitudAdapter(final List<Solicitud> solicitudLista, boolean isEditable) {
        this.solicitudLista = solicitudLista;
        this.isEditable = isEditable;
    }

    @NonNull
    @Override
    public SolicitudViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        //Vincular el adapter a la vista (cardview Solicitud). Archivo: Solicitud_cardview.xml
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_order, parent, false);
        return new SolicitudViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SolicitudViewHolder holder, final int position) {
        //Sirve para imprimir los datos en el cardview
        final Solicitud solicitud = solicitudLista.get(position);
        holder.bind(solicitud);
    }

    @Override
    public int getItemCount() {
        //Determina la cantidad de registros a asignar al recyclerView
        return solicitudLista.size();
    }

    public class SolicitudViewHolder extends RecyclerView.ViewHolder{
        //Declarar los controles que tiene el cardview de Solicitud
        private TextView txtOrigen, txtDestino, txtClase, txtTipo, txtCategoria, txtDescripcion;

        public SolicitudViewHolder(@NonNull final View itemView) {
            super(itemView);
            //Relacionar los controles del cardview con los controles declarados en java
            txtOrigen = itemView.findViewById(R.id.txtOrigen);
            txtDestino = itemView.findViewById(R.id.txtDestino);
            txtClase = itemView.findViewById(R.id.txtClase);
            txtTipo = itemView.findViewById(R.id.txtTipo);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            
        }

        public void bind(final Solicitud solicitud){
            //Asignar los datos del Solicitud a cada control del cardview
            txtOrigen.setText(solicitud.getDireccionOrigen());
            txtDestino.setText(solicitud.getDireccionDestino());
            txtClase.setText(solicitud.getClaseCarga());
            txtTipo.setText(solicitud.getTipoCarga());
            txtCategoria.setText(solicitud.getCategoriaCarga());
            txtDescripcion.setText(solicitud.getDescripcionCarga());


            if (isEditable) {
                //Evento cuando clickean al card view
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(itemView.getContext(), "GO TO DETAILS", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putInt("solicitudId", solicitud.getId());

                        NavController navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host_fragment_content_navigation);
                        navController.navigate(R.id.detalleSolicitudFragment, bundle);
                    }
                });
            }


        }

    }

}
