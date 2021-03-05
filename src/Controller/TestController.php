<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class TestController extends AbstractController
{
    /**
     * @Route("/test", name="test")
     */
    public function index(): Response
    {
        return $this->render('test/index.html.twig', [
            'controller_name' => 'TestController',
        ]);
    }
    /**
     * @Route("/t", name="t")
     */
    public function f1(): Response
    {
        return $this->render('AjouterLivraisonFront.html.twig', [
            'controller_name' => 'TestController',
        ]);
    }
}
