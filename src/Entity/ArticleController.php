<?php

namespace App\Controller;
use App\Data\SearchData;
use App\Entity\Article;

use App\Entity\Categorie;
use App\Entity\Commentaire;
use App\Form\ArticleType;

use App\Form\CommentaireType;
use App\Form\SearchForm;
use App\Repository\ArticleRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;


class ArticleController extends AbstractController
{
    /**
     * @Route("/", name="article")
     */
    public function index(ArticleRepository $repository, Request $request)
    {
        $data = new SearchData();

        $form = $this->createForm(SearchForm::class, $data);
        $form->handleRequest($request);
        $articles = $repository->findSearch($data);
        return $this->render('article/index.html.twig', [
            'articles' => $articles,
            'form' => $form->createView()
        ]);
    }

    /**
     * @Route("/listeArticles", name="showarticles")
     */

    public function listarticles()
    {
        $ar = $this->getDoctrine()->getRepository(Article::class)->findAll();
        return $this->render("article/listarticles.html.twig", array('listarticles' => $ar));

    }
    /**
     * @Route("/listeArticlesTN", name="showarticlesTN")
     */

    public function listTriearticleN()
    {
        $ar = $this->getDoctrine()->getRepository(Article::class)->listOrderByName();
        return $this->render("article/listarticles.html.twig", array('listarticles' => $ar));

    }
    /**
     * @Route("/listeArticlesTC", name="showarticlesTC")
     */

    public function listTriearticleC()
    {
        $ar = $this->getDoctrine()->getRepository(Article::class)->listOrderByCategories();
        return $this->render("article/listarticles.html.twig", array('listarticles' => $ar));

    }

    /**
     * @Route("/articles", name="showarticlesfront")
     */

    public function listarticlesfront()
    {
        $ar = $this->getDoctrine()->getRepository(Article::class)->findAll();
        return $this->render("article/AfficheFront.html.twig", array('listarticles' => $ar));

    }

    /**
     * @Route("/articles/{id}", name="showarticles1")
     */

    public function listarticlesfront1($id)
    {
        $art = $this->getDoctrine()->getRepository(Article::class)->find($id);
        return $this->render("article/Affiche1article.html.twig", array('article' => $art));

    }

    /**
     * @Route("/addArticle", name="newarticle")
     */
    public function AjouterArticle(Request $requet)
    {
        $articl = new Article();
        $form = $this->createForm(ArticleType::class, $articl);
        $em = $this->getDoctrine()->getManager();

        $form->handleRequest($requet);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $articl->getImage();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('EventImage_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $articl->setImage($fileName);
            $em->persist($articl);
            $em->flush();
            return $this->redirectToRoute("showarticles");
        }
        return $this->render("article/addArticle.html.twig", [

            'OPform' => $form->createView()
        ]);
    }

    /**
     * @Route("/updateArticle/{id}", name="updateAR" )
     */

    public function Modifierarticle(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();
        $ar = $em->getRepository(Article::class)->find($id);
        $form = $this->createForm(ArticleType::class, $ar);

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $file = $ar->getImage();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('EventImage_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $ar->setImage($fileName);
            $em->flush();
            return $this->redirectToRoute('showarticles');
        }
        return $this->render('article/addArticle.html.twig', [
            'OPform' => $form->createView(),
        ]);

    }

    /**
     * @Route("/deleteArticle/{id}", name="deleteAR" )
     */

    public function SupprimerArticle($id)
    {
        $em = $this->getDoctrine()->getManager();
        $ar = $em->getRepository(Article::class)->find($id);
        $em->remove($ar);
        $em->flush();
        return $this->redirectToRoute('showarticles');

    }

    /**
     * @Route("recherche", name="recherche")
     */
    function Recherche(ArticleRepository $repository, Request $request)
    {
        $data = $request->get('search');
        $article = $repository->findBy(['Sujet' => $data]);
        return $this->render("article/listarticles.html.twig", array("listarticles" => $article));
    }
    /**
     * @Route ("/rechercheart",name="rechercheart")
     */
    public function rechercher(ArticleRepository $repository , Request $request)
    {
        $data=$request->get('search');
        $article=$repository->SearchName($data);
        return $this->render('article/listarticles.html.twig"',array('listarticles'=>$article));
    }
    /**
     * @Route("/article/{id}", name="blog_read")
     * @param Post $post
     * @param Request $request
     * @return Response
     * @throws \Exception
     */
    public function read(Article $article, Request $request): Response
    {
        $comment = new Commentaire();
        $comment->setArticle($article);

        $form = $this->createForm(CommentaireType::class, $comment)->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->persist($comment);
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute("showarticles", ["id" => $article->getId()]);
        }

        return $this->render("article/Affiche1article.html.twig", [
            "article" => $article,
            "form" => $form->createView()
        ]);
    }

}
