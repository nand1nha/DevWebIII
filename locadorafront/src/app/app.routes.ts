import { Routes } from '@angular/router';
import { Ator } from './components/ator/ator';
import { Classe } from './components/classe/classe';
import { Diretor } from './components/diretor/diretor';
import { Home } from './components/home/home';

export const routes: Routes = [
  {
    path: '',
    component: Home
  },
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
