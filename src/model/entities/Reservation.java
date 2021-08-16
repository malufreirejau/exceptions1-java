package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public long duration(){
        long diff = checkOut.getTime() - checkIn.getTime(); //pego a diferença entre as datas em milisegundos
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); //transformo uma quantidade de milisegundos em dias
    }

//    SOLUÇÃO 2 - RUIM - A LÓGICA Q ESTAVA NO PROGRAMA PRINCIPAL VEM PARA DENTRO DO METODO UPDATEDATES E TESTO A LÓGICA
//      ANTES DE ATUALIZAR E O MÉDIGO NÃO VAI MAIS SER VOID, VAI RETORNAR UMA STRING
//
//    public void updateDates(Date checkIn, Date checkOut){
//        this.checkIn = checkIn;
//        this.checkOut = checkOut;
//    }

    public String updateDates(Date checkIn, Date checkOut){

        Date now = new Date();
        if(checkIn.before(now) || checkOut.before(now)) {
            return "Reservation dates for update must be future dates";
        }
        if(!checkOut.after(checkIn)) {
            return "Check-out date must be after check-in date";
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        return null; // se retorna nulo não deu erro, se deu erro retorna uma das duas strings acima
    }


    @Override
    public String toString() {
        return "Room "
                + roomNumber
                +", check-in: "
                + sdf.format(checkIn)
                +", check-out: "
                + sdf.format(checkOut)
                +", "
                +duration()
                +" nights";
    }

}
