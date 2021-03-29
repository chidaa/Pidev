<?php


namespace App\Form\Type;



use App\Entity\Categories;
use App\Entity\Produits;
use App\Form\CategoriesType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\DataTransformer;

class ProduitsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom', TextType::class, array('attr' => array('class' => 'form-control', 'required' => true)))
            ->add('quantite', NumberType::class, array('attr' => array('class' => 'form-control', 'required' => true)))
            ->add('categories', EntityType::class, [
                // looks for choices from this entity
                'class' => Categories::class,

                // uses the User.username property as the visible option string
                'choice_label' => 'nom',

                // used to render a select box, check boxes or radios
                // 'multiple' => true,
                // 'expanded' => true,
            ])
            ->add('prix', TextType::class, array('attr' => array('class' => 'form-control', 'required' => true)))

            ->add('save', SubmitType::class, array(
                'label' => 'Enregistrer',
                'attr' => array('class' => 'btn btn-primary mt-3')
            ));
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Produits::class,
        ]);
    }

}