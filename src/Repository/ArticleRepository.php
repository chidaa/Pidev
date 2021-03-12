<?php

namespace App\Repository;

use App\Data\SearchData;
use App\Entity\Article;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Article|null find($id, $lockMode = null, $lockVersion = null)
 * @method Article|null findOneBy(array $criteria, array $orderBy = null)
 * @method Article[]    findAll()
 * @method Article[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ArticleRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Article::class);
    }

    // /**
    //  * @return Article[] Returns an array of Article objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('a')
            ->andWhere('a.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('a.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Article
    {
        return $this->createQueryBuilder('a')
            ->andWhere('a.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    public function listOrderByName()
    {
        return $this->createQueryBuilder('a')
            ->orderBy('a.Sujet','ASC')
            ->getQuery()->getResult();
    }
    public function listOrderByCategories()
    {
        return $this->createQueryBuilder('a')
            ->orderBy('a.Categorie','ASC')
            ->getQuery()->getResult();
    }
    /**
     * Récupère les produits en lien avec une recherche
     * @return Article[]
     */
    public function findSearch(SearchData $search):array
    {
        $query = $this
            ->createQueryBuilder('a')
            ->select('c', 'a')
            ->join('a.Categorie', 'c');
        if (!empty($search->q)) {
            $query = $query
                ->andWhere('a.Sujet LIKE :q')
                ->setParameter('q', "%{$search->q}%");
        }
        if (!empty($search->categories)) {
            $query = $query
                ->andWhere('c.id IN (:categories)')
                ->setParameter('categories', $search->categories);
        }
        return $query->getQuery()->getResult();
    }
    public function SearchName($data)
    {
        return $this->createQueryBuilder('a')
            ->where('a.Sujet LIKE :data')->orWhere('a.Description Like :data ')->orWhere('a.id Like :data ')
            ->setParameter('data', '%'.$data.'%')
            ->getQuery()->getResult()
            ;
    }

}
