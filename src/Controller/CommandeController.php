<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\Livraison;
use App\Entity\Livreur;
use App\Form\CommandeType;
use App\Form\LivreurType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

class CommandeController extends AbstractController
{
    /**
     * @Route("/affcommande", name="showcommande")
     */

    public function AfficherCommande()
    {
        $commande= $this->getDoctrine()->getRepository(Commande::class)->findAll();
        return $this->render("commande/AfficherCommandeBack.html.twig",array('listcommande'=>$commande));

    }
    /**
     * @Route("/suppCommande/{id}", name="deletecommande" )
     */

    public function SupprimerCommande($id)
    {
        $em=$this->getDoctrine()->getManager();
        $doc=$em->getRepository(Commande::class)->find($id);
        $em->remove($doc);
        $em->flush();
        return $this->redirectToRoute('showcommande');

    }


    /**
     * @Route("/ajoutCommande", name="addcommande")
     */
    public function AjouterCommande(Request $requet, SessionInterface $session)
    { $session->start();

        $commande= new commande();

        $commande->setPrixtotal((float)$session->get('total'));
        $commande->setEtat("non livre");
        $em=$this->getDoctrine()->getManager();
        $em->persist($commande);
        $em->flush();
        $session->clear();
        return $this->render("front.html.twig");

    }

    /**
     * @Route("/c", name="c" )
     */

    public function index()
    {

        return $this->render("commande/AjouterCommande.html.twig");

    }
}
