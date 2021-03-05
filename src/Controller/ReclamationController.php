<?php

namespace App\Controller;

use App\Entity\Reclamation;
//use App\Form\ReclamationType;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ReclamationController extends AbstractController
{
    /**
     * @Route("/affichage_reclamation",name="affichage_reclamation")
     */
    public function shows(ReclamationRepository $repo): Response
    {

        $reclamations = $repo->findAll();

        return $this->render('Dashboard/read.html.twig', [
            'controller_name' => 'ReclamationController','reclamations'=>$reclamations
        ]);
    }
    /**
     * @Route("/affichage_acceuil",name="affichage_acceuil")
     */
    public function acceuil()
    {
    return $this->render('/Front/reclamationfront.html.twig');
    }

    /**
     * @Route("/ajouter_reclamation", name="ajouter_reclamation")
     */
    public function add(Request $request)
    {
        $rec = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $rec);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($rec);
            $entityManager->flush();
            return $this->redirectToRoute('affichage_reclamation');
        }
        return $this->render('Dashboard/create.html.twig', array(
            'format' => $form->createView(),
        ));
    }

    /**
     * @Route("/modifier_reclamation/{id}", name="modifier_reclamation")
     * @ParamConverter("reclamation", class="App:Reclamation")
     */
    public function edit(Request $request, $reclamation)
    {
        $form = $this->createForm(ReclamationType::class,$reclamation);
        $form->handleRequest($request);
        $data = $form->getData();
        $em = $this->getDoctrine()->getManager();
        $rec = $em->getRepository(Reclamation::class)->findOneBy(array('id' => $data->getId()));
        if ($form->isSubmitted()) {

            $em->persist($rec);
            $em->flush();
            return $this->redirectToRoute('affichage_reclamation');
        }

        return $this->render('Dashboard/edit.html.twig', [
            'format' => $form->createView(),
        ]);
    }

    /**
     * @Route("delete/{id}", name="supprimer_reclamation")
     */
    public function delete(Reclamation $reclamation)
    {
        $em = $this->getDoctrine()->getManager();
        $em->remove($reclamation);
        $em->flush();
        return $this->redirectToRoute('affichage_reclamation');
    }


    /**
     * @Route("/reclamation", name="reclamation")
     */
    public function index(): Response
    {
        return $this->render('reclamation/index.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    }
}
