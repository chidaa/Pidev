<?php

namespace App\Controller;

use App\Entity\Disponibilite;
use App\Form\DisponibiliteType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;


class DisponibiliteController extends AbstractController
{
    /**
     * @Route("/listedispo", name="listedispo")
     */
    public function read(): Response
    {
        $repo = $this->getDoctrine()->getRepository(Disponibilite::class);
        $listedispo = $repo->findAll();

        return $this->render('disponibilite/listedispo.html.twig', [
            'dispo' => $listedispo]);

    }

    /**
     * @param Request $request
     * @return Response
     * @Route("/ajoutdispo", name="ajouterdispo")
     */
    public function createdispo(Request $request)
    {
        $dispo = new Disponibilite();
        $form = $this->createForm(DisponibiliteType::class, $dispo);
        $form->add('Ajouter', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($dispo);
            $em->flush();
            return $this->redirectToRoute('listedispo');
        }
        return $this->render('disponibilite/ajouterdispo.html.twig', array(
            'format' => $form->createView(),
        ));
    }

    /**
     * @param Request
     * @return Response
     * @Route("/dispo/modifier/{id}" , name="dispo_modifier")
     */
    public function modifierdispo(Request $request,$id){
        $repo=$this->getDoctrine()->getRepository(Disponibilite::class);
        $dispo=$repo->find($id);
        $form=$this->createForm(DisponibiliteType::class,$dispo);
        $form->add('Modifier',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('listedispo');
        }
        else{
            return $this->render('disponibilite/modifierdispo.html.twig',[
                'format'=>$form->createView()]);
        }
    }
    /**
     * @Route("/dispo/supprimer/{id}",name="supprimerdispo")
     */
    public function supprimerdispo(int $id): Response
    {
        $em=$this->getDoctrine()->getManager();
        $repo=$this->getDoctrine()->getRepository(Disponibilite::class);
        $dispo=$repo->find($id);
        $em->remove($dispo);
        $em->flush();

        return $this->redirectToRoute('listedispo');

    }
    /**
     * @Route("/{id}", name="dispo_by_id", requirements={"id"="\d+"})
     * @ParamConverter("dispo", class="App:Disponibilite")
     */
    public function details($dispo)
    {
        return $this->render('disponibilite/detaildispo.html.twig', ['dispo'=> $dispo] );
    }
}
