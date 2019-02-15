<#include "base.ftl">
<@base title="Details"/>
<#macro bodyPage>
    <div class="navbar-fixed ">
        <nav>
            <div class="nav-wrapper indigo darken-2">
                <a href="/" class=" "><span class="logo">Finder</span></a>
                <ul class="right">
                    <li><a href="/"><i class="material-icons">search</i></a></li>
                    <li><a href="/"><i class="material-icons">view_module</i></a></li>
                    <li><a href="/"><i class="material-icons">refresh</i></a></li>
                </ul>
            </div>
        </nav>
    </div>
    <div class="row">
        <div class="col s12">
            <div class="card ">
                <div class="card-stacked">
                    <div class="card-content">
                        <span class="card-title">
                            <img width="40" height="40"
                                 src="https://d2j3q9yua85jt3.cloudfront.net/img/ff6fd9c8fca99fb1041c202f3147d577" alt=""
                                 class="circle">
                            <span class="my-card-title ">${library.artifactId}</span>
                            <span class="right usage">${library.usage}</span>
                        </span>
                        <p>${library.description}</p>
                    </div>
                    <div class="card-action">
                        <#list library.blogs as blog>
                            <div class="chip">
                                <img src="${blog.img}" alt="Contact Person">
                                ${blog.name}
                            </div>
                        </#list>
                    </div>
                    <div class="card-action">
                        <span class="card-title">Tags</span><br><br>
                        <#list library.tags as tag>
                            <div class="chip">
                                ${tag}
                            </div>
                        </#list>
                    </div>>
                    <div class="card-action">
                        <span class="card-title">Articles</span>
                        <ul class="collection">
                            <#list library.articles as article>
                                <li class="collection-item avatar">
                                    <img src="${article.url}" alt="" class="circle">
                                    <span class="title">${article.title}</span>
                                    <p>${article.body}
                                    </p>
                                </li>
                            </#list>
                        </ul>
                    </div>
                    <div class="card-action">
                        <span class="card-title">Projects</span>

                        <ul class="collection">
                            <#list library.projects as proj>
                                <li class="collection-item">
                                    <span class="title project-title"><b>${proj.name}</b></span>
                                    <a href="#!" class="secondary-content"><i class="material-icons">grade</i> ${proj.stars}</a>
                                    <p>${proj.description}
                                    </p>
                                    <span class="updated_at">Last modify: ${proj.lastModifiedDate}</span>

                                </li>
                            </#list>


                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

</#macro>