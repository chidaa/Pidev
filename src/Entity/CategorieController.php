<?php

namespace App\Controller;

use App\Entity\Categorie;

use App\Form\CategorieType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CategorieController extends AbstractController
{
    /**
     * @Route("/listec", name="showcategories")
     */

    public function listCategories()
    {
        $categorie= $this->getDoctrine()->getRepository(Categorie::class)->findAll();
        return $this->render("categorie/listecategories.html.twig",array('listc'=>$categorie));

    }

    /**
     * @Route("/categorie", name="categories")
     */
    public function index(): Response
    {
        return $this->render('categorie/index.html.twig', [
            'controller_name' => 'CategorieController',
        ]);
    }
    /**
     * @Route("/addc", name="newcategorie")
     */
    public function AjouterConsultant(Request $requet)
    {
        $categorie= new Categorie();
        $form= $this->createForm(CategorieType::class,$categorie);
        $em=$this->getDoctrine()->getManager();

        $form->handleRequest($requet);

        if($form->isSubmitted()&& $form->isValid())
        {
            $em->persist($categorie);
            $em->flush();
            return $this->redirectToRoute("showcategories");
        }
        return    $this->render("categorie/addc.html.twig",[

            'form'=>$form->createView()
        ]);
    }
    /**
     * @Route("/deletec/{id}", name="deleteCategorie" )
     */

    public function SupprimerCategorie($id)
    {
        $em=$this->getDoctrine()->getManager();
        $cat=$em->getRepository(Categorie::class)->find($id);
        $em->remove($cat);
        $em->flush();
        return $this->redirectToRoute('showcategories');

    }
    /**
     * @Route("/updatec/{id}", name="updateCategories" )
     */

    public function ModifierCategorie(Request $request,$id)
    {
        $em=$this->getDoctrine()->getManager();
        $categorie = $em->getRepository(Categorie::class)->find($id);
        $form = $this->createForm(CategorieType::class, $categorie);

        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $em->flush();
            return $this->redirectToRoute('showcategories');
        }
        return $this->render('categorie/addc.html.twig',[
            'form' => $form->createView()
        ]);

    }
    /**
     * @Route("/listep/{id}", name="showcategorie")
     */
    public function showProducts($id): Response
    {
        $category = $this->getDoctrine()
            ->getRepository(Categorie::class)
            ->findOneBy($id);

        $products = $category->getArticles();
        return $this->render("categorie/listecategories.html.twig", array('listc' => $products));
    }
}
