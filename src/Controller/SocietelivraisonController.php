<?php

namespace App\Controller;

use App\Entity\Societelivraison;
use App\Form\SocietelivraisonType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;

class SocietelivraisonController extends AbstractController
{
    /**
     * @Route("/listesociete", name="listesociete")
     */
    public function read(): Response
    {
        $repo = $this->getDoctrine()->getRepository(Societelivraison::class);
        $listesociete = $repo->findAll();

        return $this->render('societelivraison/listeSociete.html.twig', [
            'societe' => $listesociete]);

    }

    /**
     * @param Request $request
     * @return Response
     * @Route("/ajoutsociete", name="ajouter")
     */
    public function createsociete(Request $request)
    {
        $societe = new Societelivraison();
        $form = $this->createForm(SocietelivraisonType::class, $societe);
        $form->add('Ajouter', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($societe);
            $em->flush();
            return $this->redirectToRoute('listesociete');
        }
        return $this->render('societelivraison/ajoutersociete.html.twig', array(
            'format' => $form->createView(),
        ));
    }

    /**
     * @param Request
     * @return Response
     * @Route("/societe/modifier/{id}" , name="societe_modifier")
     */
    public function modifiersociete(Request $request,$id){
        $repo=$this->getDoctrine()->getRepository(Societelivraison::class);
        $societe=$repo->find($id);
        $form=$this->createForm(SocietelivraisonType::class,$societe);
        $form->add('Modifier',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('listesociete');
        }
        else{
            return $this->render('societelivraison/modifiersociete.html.twig',[
                'format'=>$form->createView()]);
        }
    }
    /**
     * @Route("/societe/supprimer/{id}",name="supprimersociete")
     */
    public function supprimersociete(int $id): Response
    {
        $em=$this->getDoctrine()->getManager();
        $repo=$this->getDoctrine()->getRepository(Societelivraison::class);
        $societe=$repo->find($id);
        $em->remove($societe);
        $em->flush();

        return $this->redirectToRoute('listesociete');

    }
    /**
     * @Route("/{id}", name="societe_by_id", requirements={"id"="\d+"})
     * @ParamConverter("societe", class="App:Societelivraison")
     */
    public function details($societe)
    {
        return $this->render('societelivraison/detailsociete.html.twig', ['societe'=> $societe] );
    }
}
