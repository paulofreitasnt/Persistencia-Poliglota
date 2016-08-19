package com.mycompany.ecommerce.entidades;

import com.mycompany.ecommerce.interfaces.MongoDBObject;
import org.bson.Document;

public class ItemCompra implements MongoDBObject<ItemCompra>{

    private int id;
    private Produto produto;
    private int quantidade;

    public ItemCompra() {
    }

    public ItemCompra(int id, Produto produto, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal(){
        return produto.getPreco()*quantidade;
    }
    
    @Override
    public String toString() {
        return "ItemCompra{" + "id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + '}';
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.append("_id", id);
        doc.append("produto", produto.toDocument());
        doc.append("quantidade", quantidade);
        return doc;
    }

    @Override
    public ItemCompra fromDocument(Document doc) {
        id = doc.getInteger("_id");
        produto = new Produto().fromDocument(doc.get("produto",Document.class));
        quantidade = doc.getInteger("quantidade");
        return this;
    }
    
}
