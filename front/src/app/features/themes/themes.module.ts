import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThemesComponent } from './themes/themes.component';
import { ThemeCardComponent } from './components/theme-card/theme-card.component';
import { HeaderModule } from 'src/app/header/header.module';

@NgModule({
  declarations: [ThemesComponent, ThemeCardComponent],
  imports: [CommonModule, HeaderModule],
  exports: [ThemesComponent],
})
export class ThemesModule {}
