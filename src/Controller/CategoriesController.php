<?php

namespace App\Controller;

use App\Entity\Categories;
use App\Form\Type\CategoriesType;
use App\Repository\CategoriesRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;


class   CategoriesController extends AbstractController
{
    /**
     * @Route("/categories", name="categories")
     */
    public function index(): Response
    {
        return $this->render('categories/.html.twig', [
            'controller_name' => 'CategoriesController',
        ]);
    }


    public function home(){
        return $this->render('categories/.html.twig');
    }
    /**
     * @Route("/categ", name="categ", methods={"GET", "POST"})
     */
    public function list( Request $request)
    {

        $repository = $this->getDoctrine()->getRepository(Categories::class);
        $items = $repository->findAll();
        return $this->render('categories/cat.html.twig',['categories'=> $items]);
    }

    /**
     * @Route("/addCategorie", name="categorie_add")
     */
    public function add(Request $request)
    {
        $categories = new Categories();
        $form = $this->createForm(\App\Form\CategoriesType::class, $categories);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($categories);
            $entityManager->flush();
            return $this->redirectToRoute('categ');
        }
        return $this->render('categories/ajouterCategorie.html.twig', array(
            'format' => $form->createView(),
        ));
    }


    /**
     * @Route("deletee/{id}", name="Categorie_delete")
     */
    public function delete(Categories $categories)
    {
        $em = $this->getDoctrine()->getManager();
        $em->remove($categories);
        $em->flush();
        return $this->redirectToRoute('categ');
    }

    /**
     * @Route ("/recherchecat",name="recherchepcat")
     */
    public function recherche(CategoriesRepository $repository , Request $request)
    {
        $data=$request->get('search');
        $categories=$repository->SearchName($data);
        return $this->render('categories/.html.twig',array('categories' => $categories));
    }

    /**
     * @Route("/listecategories", name="showcategories")
     */

    public function filterbyname()
    {
        $Categories = $this->getDoctrine()->getRepository(Categories::class)->listOrderByName();
        return $this->render("Categories/.html.twig", array('categories' => $categorie));

    }

}