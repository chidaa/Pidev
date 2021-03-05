<?php

namespace App\Form;

use App\Entity\Garantie;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\NumberType;


class GarantieType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('id_produit',TextType::class, array('attr' => array('class' => 'form-control', 'required' => true)))
            ->add('description',TextType::class, array('attr' => array('class' => 'form-control', 'required' => true)))
            ->add('id_client',NumberType::class, array('attr' => array('class' => 'form-control', 'required' => true)))
            ->add('save', SubmitType::class, array(
                'label' => 'Ajouter',
                'attr' => array('class' => 'btn btn-primary mt-3')
            ));

    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Garantie::class,
        ]);
    }
}
