package org.example;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory enf = Persistence.createEntityManagerFactory("PersistenceAppPU");
        EntityManager en = enf.createEntityManager();

        try {
            en.getTransaction().begin();

            Factura factura1 = new Factura();

            factura1.setNumero(12);
            factura1.setFecha("10/08/202");

            Domicilio dom = new Domicilio("San Martin", 1222);
            Cliente cliente = new Cliente("Daniel","Cabrero",45023058);
            cliente.setDomicilio(dom);
            dom.setCliente(cliente);

            factura1.setCliente(cliente);

            Categoria perecederos = new Categoria("Percederos");
            Categoria lacteos = new Categoria("Lacteos");
            Categoria limpieza = new Categoria("Limpieza");

            Articulo art1 = new Articulo(200,"Yogur",50);
            Articulo art2 = new Articulo(300,"Detergente",40);

            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(art1);
            perecederos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            DetalleFactura det1 = new DetalleFactura();

            det1.setArticulo(art1);
            det1.setCantidad(2);
            det1.setSubtotal(40);

            art1.getDetalle().add(det1);
            factura1.getDetalle().add(det1);
            det1.setFactura(factura1);

            DetalleFactura det3 = new DetalleFactura();

            det3.setArticulo(art2);
            det3.setCantidad(1);
            det3.setSubtotal(30);

            art2.getDetalle().add(det3);
            factura1.getDetalle().add(det3);
            det3.setFactura(factura1);

            factura1.setTotal(150);

            en.persist(factura1);

            en.flush();

            en.getTransaction().commit();

        } catch (Exception e) {

            en.getTransaction().rollback();

        }

        en.close();
        enf.close();
    }
}