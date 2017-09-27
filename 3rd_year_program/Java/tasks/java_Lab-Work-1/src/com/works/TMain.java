package com.works;

import java.io.*;
import java.util.LinkedList;

public class TMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        /*
        TASK serializing as the whole object
         */
        System.out.println("Task 1");
        // create linked list
        myLinkedList myList = new myLinkedList();
        // add element to it
        myList.add("David");
        myList.add("Lourece");
        myList.add(15);

        // create stream to the file
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("out.txt")));

        // write into file serialized data
        out.writeObject(myList);

        // close file (important!)
        out.close();

        // define linked list for future value
        myLinkedList myNewList;

        // create stream from the file
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("out.txt")));

        // read from the file serialized data and deserialize it
        myNewList = (myLinkedList)in.readObject();

        // close file (important!)
        in.close();

        System.out.println("__BEFORE_SERIALIZATION__");
        System.out.println(myList.toString());
        System.out.println();
        System.out.println("__AFTER_DESERIALIZATION__");
        System.out.println(myNewList.toString());

        /*
        TASK serializing as the sequence of generic objects
         */
        System.out.println("\nTask 2");
        // create two banks (Bank : base class, BankAlpha : derived)
        Bank a = new Bank("A", 1.3);
        Bank a1 = new Bank("B", 3.2);
        BankAlpha b = new BankAlpha();
        BankUkrSoc c = new BankUkrSoc();
        // create two clients
        Client cl1 = new Client("Mark", 1000);
        Client cl2 = new Client("David", 502.3);
        // add clients to the alpha bank
        b.addClient(cl1);
        b.addClient(cl2);
        // add clients to the UkrSoc bank
        c.addClient(cl1);
        c.addClient(cl2);
        // initialize generic object
        BanksGeneric<Bank> myBank = new BanksGeneric<>();
        // set bank to generic class
        myBank.addBank(a);
        myBank.addBank(a1);
        // create stream
        ObjectOutputStream outb = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("outb.txt")));
        // write to file serialized data
        outb.writeObject(myBank);
        // close file (important!)
        outb.close();

        // create stream
        ObjectInputStream inb = new ObjectInputStream(new BufferedInputStream(new FileInputStream("outb.txt")));
        // read from file deserialized data
        BanksGeneric<Bank> myNewBank = (BanksGeneric<Bank>) inb.readObject();
        // close file (important!)
        inb.close();

        System.out.println("__BEFORE_SERIALIZATION__");
        System.out.println(myBank.toString());
        System.out.println();
        System.out.println("__AFTER_DESERIALIZATION__");
        System.out.println(myNewBank.toString());

        /*
        TASK serializing as the string
         */
        System.out.println("\nTask 3");
        LinkedList<Bank> banks = new LinkedList<>();

        Bank a_ = new Bank("A", 1.1);
        Bank b_ = new Bank("B", 1.2);
        Bank c_ = new Bank("C", 1.3);
        Bank d_ = new Bank("D", 1.4);

        banks.add(a_);
        banks.add(b_);
        banks.add(c_);
        banks.add(d_);

        readWriteAsString<LinkedList<Bank>> o = new readWriteAsString<>(banks);

        o.write_to("outs.txt");
        String s = o.read_from("outs.txt");

        System.out.println("__BEFORE_SERIALIZATION__");
        System.out.println(banks.toString());
        System.out.println("__AFTER_DESERIALIZATION__");
        System.out.println(s);
    }
}