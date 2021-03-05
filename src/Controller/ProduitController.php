<?php

namespace App\Controller;

use App\Entity\Produits;
use App\Form\Type\ProduitsType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;


class   ProduitController extends AbstractController
{
    /**
     * @Route("/produit", name="produit")
     */
    public function index(): Response
    {
        return $this->render('produit/index.html.twig', [
            'controller_name' => 'ProduitController',
        ]);
    }


    public function home(){
        return $this->render('produit/home.html.twig');
    }
    /**
     * @Route("/", name="produit_list", methods={"GET", "POST"})
     */
    public function list( Request $request)
    {

        $repository = $this->getDoctrine()->getRepository(Produits::class);
        $items = $repository->findAll();
        return $this->render('produit/home.html.twig',['produits'=> $items]);
    }
    /**
     * @Route("/addProduct", name="produit_add")
     */
    public function add(Request $request)
    {
        $produit = new Produits();
        $form = $this->createForm(ProduitsType::class, $produit);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($produit);
            $entityManager->flush();
            return $this->redirectToRoute('produit_list');
        }
        return $this->render('produit/ajouterProduit.html.twig', array(
            'format' => $form->createView(),
        ));
    }
    /**
     * @Route("/edit/{id}", name="produit_edit")
     * @ParamConverter("produit", class="App:Produits")
     */
    public function editClient(Request $request, $produit)
    {
        $form = $this->createForm(ProduitsType::class,$produit);
        $form->handleRequest($request);
        $data = $form->getData();
        $em = $this->getDoctrine()->getManager();
        $produit = $em->getRepository(Produits::class)->findOneBy(array('id' => $data->getId()));
        if ($form->isSubmitted()) {

            $em->persist($produit);
            $em->flush();
            return $this->redirectToRoute('produit_list');
        }

        return $this->render('Produit/modifierProduit.html.twig', [
            'format' => $form->createView(),
        ]);
    }

    /**
     * @Route("delete/{id}", name="produit_delete")
     */
    public function delete(Produits $produits)
    {
        $em = $this->getDoctrine()->getManager();
        $em->remove($produits);
        $em->flush();
        return $this->redirectToRoute('produit_list');
    }

}
