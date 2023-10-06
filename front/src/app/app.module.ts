import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { HeaderModule } from './header/header.module';
import { LoggedInHomeComponent } from './pages/logged-in-home/logged-in-home.component';
import { PostComponent } from './pages/post/post.component';
import { UserProfilComponent } from './pages/user-profil/user-profil.component';
import { ThemesModule } from './features/themes/themes.module';
import { ThemesPageComponent } from './pages/themes-page/themes-page.component';
import { CreatePostComponent } from './pages/create-post/create-post.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoggedInHomeComponent,
    PostComponent,
    UserProfilComponent,
    ThemesPageComponent,
    CreatePostComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    FormsModule,
    HttpClientModule,
    HeaderModule,
    ThemesModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
