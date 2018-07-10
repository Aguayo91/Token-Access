public class MainActivity extends AppCompatActivity {

        TextView fechaCompleta;
        TextView hora;
        TextView dia;
        TextView mes;
        TextView año;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date d=new Date();

        //SACAMOS LA FECHA COMPLETA
        fechaCompleta = (TextView) findViewById(R.id.
        txtFechaCompleta);
        SimpleDateFormat fecc=new SimpleDateFormat("d, MMMM
        'del' yyyy");
        String fechacComplString = fecc.format(d);
        fechaCompleta.setText(fechacComplString);



        //SACAMOS LA HORA
        hora= (TextView) findViewById(R.id.txtHora);
        SimpleDateFormat ho=new SimpleDateFormat("h:mm a");
        String horaString = ho.format(d);
        hora.setText(horaString);


        // SACAMOS EL DIA
        dia = (TextView) findViewById(R.id.txtDia);
        SimpleDateFormat di=new SimpleDateFormat("EEEE");
        String currentDateTimeStrin = di.format(d);
        dia.setText(currentDateTimeStrin);

        //SACAMOS EL MES
        mes = (TextView) findViewById(R.id.txtMes);
        SimpleDateFormat me=new SimpleDateFormat("MMMM");
        String mesString = me.format(d);
        mes.setText(mesString);

        //SACAMOS EL AÑO
        año = (TextView) findViewById(R.id.txtAño);
        SimpleDateFormat an=new SimpleDateFormat("yyyy");
        String añoString = an.format(d);
        año.setText(añoString);

    }
}

      Fecha ejemplo sencillo descargar:
'https://drive.google.com/file/d/0BxbENHeONKoZUDc0YUN4ckFRc2s'
