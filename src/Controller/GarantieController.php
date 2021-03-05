<?php

namespace App\Controller;

use App\Entity\Garantie;
use App\Form\GarantieType;
use App\Repository\GarantieRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;


class GarantieController extends AbstractController
{
    /**
     * @Route("/affichage_garantie",name="affichage_garantie")
     */
    public function shows(GarantieRepository $repo): Response
    {

        $garanties = $repo->findAll();

        return $this->render('Dashboard/affichagegarantie.html.twig', [
            'controller_name' => 'GarantieController','garanties'=>$garanties
        ]);
    }
    /**
     * @Route("/ajouter_garantie", name="ajouter_garantie")
     */
    public function add(Request $request)
    {
        $garan = new Garantie();
        $form = $this->createForm(GarantieType::class, $garan);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($garan);
            $entityManager->flush();
            return $this->redirectToRoute('affichage_garantie');
        }
        return $this->render('Dashboard/ajoutergarantie.html.twig', array(
            'format' => $form->createView(),
        ));
    }

    /**
     * @Route("/modifier_garantie/{id}", name="modifier_garantie")
     * @ParamConverter("garantie", class="App:Garantie")
     */
    public function edit(Request $request, $garantie)
    {
        $form = $this->createForm(GarantieType::class,$garantie);
        $form->handleRequest($request);
        $data = $form->getData();
        $em = $this->getDoctrine()->getManager();
        $rec = $em->getRepository(Garantie::class)->findOneBy(array('id' => $data->getId()));
        if ($form->isSubmitted()) {

            $em->persist($rec);
            $em->flush();
            return $this->redirectToRoute('affichage_garantie');
        }

        return $this->render('Dashboard/modifiergarantie.html.twig', [
            'format' => $form->createView(),
        ]);
    }

    /**
     * @Route("delete/{id}", name="supprimer_garantie")
     */
    public function delete(Garantie $garantie)
    {
        $em = $this->getDoctrine()->getManager();
        $em->remove($garantie);
        $em->flush();
        return $this->redirectToRoute('affichage_garantie');
    }

    /**
     * @Route("/garantie", name="garantie")
     */
    public function index(): Response
    {
        return $this->render('garantie/index.html.twig', [
            'controller_name' => 'GarantieController',
        ]);
    }
}
