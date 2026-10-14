import { Routes } from '@angular/router';
import { Ator } from './components/ator/ator';
import { Classe } from './components/classe/classe';
import { Diretor } from './components/diretor/diretor';
import { Home } from './components/home/home';
import { Item } from './components/item/item';
import { Titulo } from './components/titulo/titulo';

export const routes: Routes = [
  {
    path: 'home',
    component: Home,
    children: [
      { path: 'ator', component: Ator},
      { path: 'classe', component: Classe},
      { path: 'diretor', component: Diretor},
      { path: 'titulo',  component: Titulo },   
      { path: 'item',    component: Item },
      { path: '', redirectTo: 'ator', pathMatch: 'full' } 
    ]
  },

  { path: '', redirectTo: '/home', pathMatch: 'full' }
     
];
