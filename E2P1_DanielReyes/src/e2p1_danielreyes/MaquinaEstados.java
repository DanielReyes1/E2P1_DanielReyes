
package e2p1_danielreyes;

import java.util.ArrayList;

public class MaquinaEstados {
    //ATRIBUTOS
    private ArrayList<String> estados = new ArrayList<>();
    private ArrayList<String> estados_aceptacion = new ArrayList<>();
    private ArrayList<String> aristas = new ArrayList<>();
    private String estado_actual;
    
    public MaquinaEstados(String estados, String aristas){
        this.aristas = split(aristas,';');
        extractAcceptNodes();
        this.estados = split(estados, ';');
        this.estado_actual = this.estados.get(0);
    }

    public ArrayList<String> getEstados() {
        return estados;
    }
    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }
    public ArrayList<String> getEstados_aceptacion() {
        return estados_aceptacion;
    }
    public void setEstados_aceptacion(ArrayList<String> estados_aceptacion) {
        this.estados_aceptacion = estados_aceptacion;
    }
    public ArrayList<String> getAristas() {
        return aristas;
    }
    public void setAristas(ArrayList<String> aristas) {
        this.aristas = aristas;
    }
    public String getEstado_actual() {
        return estado_actual;
    }
    public void setEstado_actual(String estado_actual) {
        this.estado_actual = estado_actual;
    }
    // substring, contains y split
    public ArrayList<String> split(String str, char delim){
        ArrayList<String> temp = new ArrayList<>();
        String [] token = str.split(Character.toString(delim));
        for (int i = 0; i < token.length; i++) {
            temp.add(token[i]);
        }
        return temp;
        
    }
    public void extractAcceptNodes(){
        String temporal = "";
        for (int i = 0; i < estados.size(); i++) {
            if(estados.get(i).contains(".")){
                temporal=estados.get(i).substring(1);
                estados_aceptacion.add(temporal);
                estados.set(i, temporal);
            }
            
        }
    }
    public String getArista(String str){
        for (int i = 0; i < aristas.size(); i++) {
            if(aristas.get(i).contains(str)){
                return aristas.get(i);
            }
        }
        return "";
    }
    
    
    public String computeStr(String str){
        String temporal = "";
        estado_actual = estados.get(0);
        estados_aceptacion = estados;
        for (int i = 0; i < str.length(); i++) {
            String a = estado_actual +"," + str.charAt(i);
            String b = getArista(a);
            if ( !b.equals("")){
                temporal += estado_actual + ":" + str.charAt(i)+ "->";
                String [] array = b.split(",");
                estado_actual = array[2];
                temporal += estado_actual + "\n";
            }else{
                temporal += "Rechazado";
                return temporal;
            }
        }
            if(estados_aceptacion.contains(estado_actual)){
                temporal += "Rechazado";
                return temporal;
            }
            else{
                temporal += "Aceptado";
                return temporal;
            }
        
    }
    
}
