package com.mycompany.ecommerce.dao;

import com.mycompany.ecommerce.entidades.Compra;
import com.mycompany.ecommerce.entidades.ItemCompra;
import com.mycompany.ecommerce.entidades.Produto;
import com.mycompany.ecommerce.enumerations.RelTypes;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class CompraDaoNeo4j {

    private String path;
    GraphDatabaseService graph;
    
    public CompraDaoNeo4j(){
        path = "/usr/local/neo4j-community-3.0.4/data/databases/graph.db";
        graph = new GraphDatabaseFactory()
                .newEmbeddedDatabase(new File(path));
    }

    public boolean create(Compra c){
        
        try(Transaction tx = graph.beginTx()){
            
            Node usuario = graph.findNode(Label.label("Usuario"), "id", c.getUsuario().getId());
            if(usuario==null){
                usuario = graph.createNode(Label.label("Usuario"));
                usuario.setProperty("id", c.getUsuario().getId());
            }
            
            List<Node> produtos = new ArrayList<>();
            
            for(ItemCompra item : c.getItens()){
                Node produto = graph.findNode(Label.label("Produto"), "id", item.getProduto().getId());
                if(produto==null){
                    produto = graph.createNode(Label.label("Produto"));
                    produto.setProperty("id", item.getProduto().getId());
                }
                produtos.add(produto);
            }
            
            for(Node n : produtos){
                usuario.createRelationshipTo(n, RelTypes.COMPROU);
            }
            
            tx.success();
            
        }
        
        return true;
        
    }
    
    public List<Integer> recomendados(Produto p){
       
        List<Integer> recomendados = new ArrayList<>();
        
        try(Transaction tx = graph.beginTx()){
            
            String query = "MATCH (p:Produto)<-[:COMPROU]-(:Usuario)-[:COMPROU]->"
                    + "(p2:Produto) WHERE p.id="+ p.getId() +" RETURN  p2.id";
            Result result = graph.execute(query);
            
            ResourceIterator<Integer> resourceIterator = result.columnAs("p2.id");
            while(resourceIterator.hasNext()){
                recomendados.add(resourceIterator.next());
            }
            
        }
        
        return recomendados;
        
    }
    
    
    
}
