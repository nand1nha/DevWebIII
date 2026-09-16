import { Routes } from '@angular/router';
import { Ator } from './components/ator/ator';
import { Classe } from './components/classe/classe';
import { Diretor } from './components/diretor/diretor';

export const routes: Routes = [


  {
    path: 'ator',
    component: Ator
  },

  {
    path: 'classe',
    component: Classe
  },

  {
    path: 'diretor',
    component: Diretor
  }
     
];
