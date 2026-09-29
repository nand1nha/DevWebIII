import { Routes } from '@angular/router';
import { Ator } from './components/ator/ator';
import { Classe } from './components/classe/classe';
import { Diretor } from './components/diretor/diretor';
import { Home } from './components/home/home';

export const routes: Routes = [
  {
    path: 'home',
    component: Home,
    children: [
      { path: 'ator', component: Ator},
      { path: 'classe', component: Classe},
      { path: 'diretor', component: Diretor},
      // Rota padrão: ao entrar em /home, redireciona para /home/ator
      { path: '', redirectTo: 'ator', pathMatch: 'full' } 
    ]
  },
  // Rota padrão do sistema
  { path: '', redirectTo: '/home', pathMatch: 'full' }
     
];
