package com.mycompany.ecommerce.app;

import com.mycompany.ecommerce.dao.CompraDaoMongodb;
import com.mycompany.ecommerce.dao.CompraDaoNeo4j;
import com.mycompany.ecommerce.entidades.Compra;
import com.mycompany.ecommerce.entidades.ItemCompra;
import com.mycompany.ecommerce.entidades.Produto;
import com.mycompany.ecommerce.entidades.Usuario;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class App {

    private static CompraDaoMongodb daoMongo = new CompraDaoMongodb();
    private static CompraDaoNeo4j daoNeo4j = new CompraDaoNeo4j();
    
    public static void main(String[] args){
        
        // Compra 1
        Usuario joao = new Usuario(1, "João");
        Produto notebook = new Produto(1, "Notebook", 2500);
        Produto mouse = new Produto(2, "Mouse", 30);

        ItemCompra item1 = new ItemCompra(1, notebook, 1);
        ItemCompra item2 = new ItemCompra(2, mouse, 1);
        
        Compra compra = new Compra(1, joao);
        compra.addItem(item1);
        compra.addItem(item2);
        
        // Compra 2
        Usuario maria = new Usuario(2, "Maria");
        Produto teclado = new Produto(3, "Teclado", 50);
        
        ItemCompra item3 = new ItemCompra(3, mouse, 1);
        ItemCompra item4 = new ItemCompra(4, teclado,1);
        
        Compra compra2 = new Compra(2, maria);
        compra2.addItem(item3);
        compra2.addItem(item4);
        
        // Compra 3
        Usuario pedro = new Usuario(3,"Pedro");
        Produto monitor = new Produto(4, "Monitor", 400);
        
        ItemCompra item5 = new ItemCompra(5, monitor, 1);
        ItemCompra item6 = new ItemCompra(6, mouse, 1);
        
        Compra compra3 = new Compra(3, pedro);
        compra3.addItem(item5);
        compra3.addItem(item6);
        
        // Inserir as compras
        daoMongo.create(compra);
//        daoNeo4j.create(compra);
        daoMongo.create(compra2);
//        daoNeo4j.create(compra2);
        daoMongo.create(compra3);
//        daoNeo4j.create(compra3);

//        System.out.println(daoMongo.read(1));


    }
    
}
