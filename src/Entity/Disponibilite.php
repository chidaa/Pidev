<?php

namespace App\Entity;

use App\Repository\DisponibiliteRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=DisponibiliteRepository::class)
 */
class Disponibilite
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank()
     */
    private $jours;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank()
     */
    private $region;

    /**
     * @ORM\OneToOne(targetEntity=Societelivraison::class, cascade={"persist", "remove"})
     */
    private $societes;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getJours(): ?string
    {
        return $this->jours;
    }

    public function setJours(string $jours): self
    {
        $this->jours = $jours;

        return $this;
    }

    public function getRegion(): ?string
    {
        return $this->region;
    }

    public function setRegion(string $region): self
    {
        $this->region = $region;

        return $this;
    }

    public function getSocietes(): ?Societelivraison
    {
        return $this->societes;
    }

    public function setSocietes(?Societelivraison $societes): self
    {
        $this->societes = $societes;

        return $this;
    }
}
