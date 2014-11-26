package domain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by haverlantmatthias on 13/11/2014.
 */
public class Graph {
    private List<Vertex> vertices = new ArrayList<Vertex>();

    public Graph(Vertex... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    public int getDistance(String from, String to) {
        /*Test Graph vide*/
        int distance = 0;
        int Ifrom = 0;
        int Ichemin = 0;
        int Ito = 0;
        List<Integer> dist = new ArrayList<Integer>();
        if (vertices.size() == 0) {
            System.out.println("Graph Vide");
        }
        else {
            for (Vertex vertex : vertices) {
                if (vertex.getName() == from) {
                    Ifrom = 1;
                    List<Edge> Edges = vertex.getEdges();
                    for (Edge Ed : Edges) {
                        if (Ed.getTarget().getName() == to) {
                            Ichemin = 1;
                            dist.add(Ed.getDistance());
                            System.out.print("Chemin dirrecte sans changement");
                        }
                    }

                    for (Edge Ed : Edges) {
                        List<Vertex> Verti = new ArrayList<Vertex>();
                        if ((Apartient(Ed.getTarget(), vertices) == true )&& (Ed.getTarget().getName()!= from)) {
                            Verti.add(Ed.getTarget());
                            for (Vertex V : Verti) {
                                List<Edge> Edgs = V.getEdges();
                                for (Edge Edg : Edgs) {
                                    if ((Edg.getTarget().getName() == to) && (Edg.getTarget().getName() != from) && (Edg.getTarget()!=V)) {
                                        Ichemin = 1;
                                        dist.add(Edg.getDistance() + Ed.getDistance());
                                        System.out.print("Chemin avec un changement");
                                    }
                                }
                            }
                        }
                    }

                    for (Edge Ed : Edges) {
                        List<Vertex> Interm1 = new ArrayList<Vertex>();
                        if (Apartient(Ed.getTarget(), vertices) == true && Ed.getTarget().getName() != from) {
                            Interm1.add(Ed.getTarget());
                            for (Vertex v : Interm1) {
                                List<Edge> Edgs1 = v.getEdges();
                                for (Edge Ed1 : Edgs1) {
                                    List<Vertex> Interm2 = new ArrayList<Vertex>();
                                    if (Apartient(Ed1.getTarget(), vertices) == true && Ed1.getTarget().getName()!=from && Ed1.getTarget()!= v) {
                                        Interm2.add(Ed1.getTarget());
                                        for (Vertex interm2 : Interm2) {
                                            List<Edge> Edgs2 = interm2.getEdges();
                                            for (Edge Ed2 : Edgs2) {
                                                if (Ed2.getTarget().getName() == to && Ed2.getTarget().getName()!=from && Ed2.getTarget()!= v && Ed2.getTarget() != interm2 ) {
                                                    Ichemin = 1;
                                                    dist.add(Ed2.getDistance() + Ed1.getDistance() + Ed.getDistance());
                                                    System.out.print("Chemin avec deux changement");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                if (vertex.getName() == to) {
                    Ito = 1;
                }
            }
            if (dist.size() != 0) {
                distance = Minimum(dist);
            }
        }
        if (Ifrom == 0) {
            System.out.println("Pas de point de depart trouve");
        }
        if (Ito == 0) {
            System.out.println("Pas de point de depart trouve");
        }
        if (Ichemin == 0) {
            System.out.println("Pas de chemin trouve");
        }
        return distance;
    }


    public boolean Apartient(Vertex vertex, List<Vertex> vertice){
        boolean resultat = false;
        for (Vertex vert : vertice) {
            if (vert == vertex) {
                resultat = true;
            }
        }
        return resultat;
    }
    public int Minimum(List<Integer> L){
        int min = Integer.MAX_VALUE;
        for (int element: L){
            if (element < min){
                min= element;
            }
        }
        return min;
    }

}