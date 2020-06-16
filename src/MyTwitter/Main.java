package MyTwitter;

//Danilo Palheta Meireles


import java.util.Vector;
import tweet.Tweet;
import MyTwitter.*;
import repositorio.*;
import perfil.*;
import exception.*;

public class Main {    
    public static void main(String[] args) throws PEException, PIException, MFPException, PDException, SIException { 
                
        VectorPerfis perfis = new VectorPerfis();
        MyTwitter myTwitter = new MyTwitter(perfis);
        
        // criando perfis ----------------------------------        
        PessoaFisica perfil2 = new PessoaFisica("Maiara");
        myTwitter.criarPerfil(perfil2);
        
        PessoaFisica perfil1 = new PessoaFisica("Danilo");      
        myTwitter.criarPerfil(perfil1);
        perfil1.setCpf(12345);
        
        PessoaFisica perfil3 = new PessoaFisica("Eduardo");
        myTwitter.criarPerfil(perfil3);
        
        // testando método seguir -------------------------
        myTwitter.seguir("Maiara", "Danilo");
        myTwitter.seguir("Maiara", "Eduardo");
        myTwitter.seguir("Danilo", "Maiara");                
        myTwitter.seguir("Eduardo", "Danilo");        
        
        // testando método tweetar ------------------------
        myTwitter.tweetar("Danilo", "Primeiro tweet");
        myTwitter.tweetar("Danilo", "Segundo tweet");
        myTwitter.tweetar("Maiara", "Primeiro tweet da Maiara.");
        
        // testando método getTimeline --------------------
        Vector<Tweet> tweet = perfil1.getTimeline();
        System.out.println("\n-> Timeline de Danilo:\n");
        for( int i=0;i<tweet.size();i++ ) {
            System.out.println(tweet.get(i).getMensagem());
        }

        // testando método tweets -------------------------
        Vector<Tweet> tweet2 = myTwitter.tweets("Danilo");
        System.out.println("\n-> Tweets de Danilo:\n");
        for( int i=0;i<tweet2.size();i++ ) {
            System.out.println(tweet2.get(i).getMensagem());
        }

        // testando método seguidores ---------------------
        Vector<Perfil> seguidores = myTwitter.seguidores("Danilo");
        System.out.println("\n-> Seguidores:\n");
        for( int i=0;i<seguidores.size();i++ ) {
            System.out.println(seguidores.get(i).getUsuario());
        }

        // testando método seguidos -----------------------
        Vector<Perfil> seguidos = myTwitter.seguidos("Danilo");
        System.out.println("\n-> Seguidos:\n");
        for( int i=0;i<seguidos.size();i++ ) {
            System.out.println(seguidos.get(i).getUsuario());
        }

        // testando método numeroSeguidores ---------------
        int numSeg = myTwitter.numeroSeguidores("Danilo");
        System.out.println("\n-> Numero de seguidores de Danilo: "+numSeg+"\n");

        // testando método cancelarPerfil -----------------
        try {
           myTwitter.cancelarPerfil("Danilo");
           Vector<Tweet> tweet3 = myTwitter.timeline("Danilo");
           System.out.println("\nTimeline:\n");
           for( int i=0;i<tweet3.size();i++ ) {
               System.out.println(tweet3.get(i).getMensagem());
           }
        }
        catch(PDException pdexception) {
            System.out.println("Perfil Danilo desativado.");
        }
        catch(PIException piexception) {
            System.out.println("Perfil Danilo inexistente.");
        }
    }
}
