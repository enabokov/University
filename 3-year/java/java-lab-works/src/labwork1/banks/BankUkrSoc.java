package com.solutions.labwork1.banks;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BankUkrSoc extends Bank {
    protected int number_of_clients;
    protected Client[] clients;
    protected double time;
    protected int i_num_client;

    public BankUkrSoc(String name, double r1, double r2, int number_of_clients){
        super(name, r1, r2);
        this.number_of_clients = number_of_clients;
        this.clients = new Client[number_of_clients];
        this.time = 1.54;
        this.i_num_client = 0;

    }

    @Override
    public void addClient(Client client){
        if(this.i_num_client == 2){
            System.out.printf("Unfortunately, we are not able to give you out the credit, %s.\n", client.myName);
            return;
        }
        clients[this.i_num_client] = client;
        client.myCredit = client.wantMoney * interestRate_1 * time;
        client.nameBank = "UkrSoc";
        client.rate = interestRate_1;
        this.i_num_client++;
    }

    void setTime(double t) { this.time = t; }

    String getClient(int index) {
        if (index < 0 || index > this.i_num_client - 1){
            return null;
        }

        return this.clients[index].toString();
    }

    int getTotalPossibleClients(){
        return this.number_of_clients;
    }

    int getCurrentNumClients(){
        return this.i_num_client;
    }

    double getTime(){
        return time;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(this.myName);
        out.writeObject(this.interestRate_1);
        out.writeObject(this.time);
        out.writeObject(this.i_num_client);
        for(int i = 0; i < this.i_num_client; i++) {
            out.writeObject(this.clients[i]);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        try {
            this.myName = (String)in.readObject();
            this.interestRate_1 = (double)in.readObject();
            this.time = (double)in.readObject();
            this.i_num_client = (int)in.readObject();
            clients = new Client[this.i_num_client];
            for(int i = 0; i < this.i_num_client; i++) {
                this.clients[i] = (Client)in.readObject();
            }
        } catch (ClassNotFoundException e){
            e.getException();
        }
    }

    public String toString(){

        StringBuilder s = new StringBuilder();
        s.append("[\n");
        for(int i =0; i < getCurrentNumClients(); i++) {
            s.append(String.format(" 'client %d': %s,\n", i+1, this.clients[i].toString()));
        }
        s.append(" ]");
        return String.format("BankUkrSoc [\n 'name': %s,\n 'interest rate 1': %s,\n 'interest rate 2': %s,\n 'time': %f,\n" +
                        " %s\n]",
                this.getMyName(),
                this.getInterestRate_1(),
                this.getInterestRate_2(),
                this.getTime(),
                s.toString());
    }
}
