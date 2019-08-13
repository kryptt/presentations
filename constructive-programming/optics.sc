     //What could go wrong here?!/
     type Gender = Boolean
     type BirthDate = LocalDate

     case class Person(name: String, birthDate: BirthDate, gender: Gender)
     case class Man(name: String, birthDate: BirthDate)

     val personToMan: Person => Option[Man] = _ match {
       case Person(name, birthDate, True) => Some(Man(name, birthDate))
       case p => None

     }

     val manToPerson: Man => Person = m => Person(m.name, m.birthDate, True)

     val getTime: IO[LocalDate] = ???

     val personAge: Person => IO[Int] = p => getTime.map(p.birthDate.yearsBetween)

     val manAge_1: Man => Option[IO[Int]] = ???
     val manAge_2: Man => IO[Int] = ???

     case class Lens[S, A](get: S => A, set: A => S => S) {
       def modify(A => A): S => S = ???

       def compose[T](other: Lens[T, S]): Lens[T, A] = ???

       def choice[T](other: Lens[T, A]): Lens[S \/ T, A] = ???

       def split[T, B](other: Lens[T, B]): Lens[S /\ T, A /\ B] = ???

     }

     //What could go wrong here?!/
     trait Functor[F[_]] {

        def map[A, B](fa: F[A])(f: A => B): F[B] = ???
 
      }

      //What could go wrong here?!/
     case class Const[A, B](a: A) implicit def constFunctor[AA] = new
     Functor[Const[AA, ?]] {

     def map[A, B](fa: Const[AA, A])(f: A => B): Const[AA, B] = ???

   }

   type TVL[S, A] = (Const[A, S], A => S) implicit def tvlFunctor[A] = new
     Functor[TVL[?, A]] {

     def map[S, T](fa: TVL[S, A])(f: S => T): TVL[T, A] = ???

   } trait Lens[S, A] {
     def apply[F[_]: Functor](A => F[A]): S => F[S]
     def get(s: S): A = ???
     def set(a: A, s: S): S = ???
     def modify(A => B): Lens[S, B] = ???
     def compose[T](other: Lens[T, S]): Lens[T, A] = ???
     def choice[T](other: Lens[T, A]): Lens[S \/ T, A] = ???
     def split[T, B](other: Lens[T, B]): Lens[S /\ T, A /\ B] = ???
   }